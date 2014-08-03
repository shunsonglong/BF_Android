package com.dt.zero.network;

import org.json.JSONException;
import org.json.JSONObject;

import com.dt.zero.R;
import com.dt.zero.app.AppMgr;
import com.dt.zero.util.CommonException;
import com.dt.zero.util.Utility;

import android.text.TextUtils;
import android.util.Log;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class JavaHttpUtility {
	private static final String TAG = "JavaHttpUtility";

	private static final int CONNECT_TIMEOUT = 10 * 1000;
	private static final int READ_TIMEOUT = 10 * 1000;

	public class NullHostNameVerifier implements HostnameVerifier {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(
				java.security.cert.X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(
				java.security.cert.X509Certificate[] certs, String authType) {
		}
	} };

	public JavaHttpUtility() {

		// allow Android to use an untrusted certificate for SSL/HTTPS
		// connection
		// so that when you debug app, you can use Fiddler http://fiddler2.com
		// to logs all HTTPS traffic
		try {
			if (true) {
				HttpsURLConnection
						.setDefaultHostnameVerifier(new NullHostNameVerifier());
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc
						.getSocketFactory());
			}
		} catch (Exception e) {
		}
	}

	public String executeNormalTask(HttpMethod httpMethod, String url,
			Map<String, String> param) throws CommonException {
		switch (httpMethod) {
		case Post:
			return doPost(url, param);
		case Get:
			return doGet(url, param);
		default:
			break;
		}
		return "";
	}

	private static Proxy getProxy() {
		String proxyHost = System.getProperty("http.proxyHost");
		String proxyPort = System.getProperty("http.proxyPort");
		if (!TextUtils.isEmpty(proxyHost) && !TextUtils.isEmpty(proxyPort)) {
			return new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(
					proxyHost, Integer.valueOf(proxyPort)));
		} else {
			return null;
		}
	}

	public String doPost(String urlAddress, Map<String, String> param)
			throws CommonException {
		AppMgr mContext = AppMgr.getInstance();
		String errorStr = mContext.getString(R.string.timeout);
		try {
			URL url = new URL(urlAddress);
			Proxy proxy = getProxy();
			HttpsURLConnection uRLConnection;
			if (proxy != null) {
				uRLConnection = (HttpsURLConnection) url.openConnection(proxy);
			} else {
				uRLConnection = (HttpsURLConnection) url.openConnection();
			}

			uRLConnection.setDoInput(true);
			uRLConnection.setDoOutput(true);
			uRLConnection.setRequestMethod("POST");
			uRLConnection.setUseCaches(false);
			uRLConnection.setConnectTimeout(CONNECT_TIMEOUT);
			uRLConnection.setReadTimeout(READ_TIMEOUT);
			uRLConnection.setInstanceFollowRedirects(false);
			uRLConnection.setRequestProperty("Connection", "Keep-Alive");
			uRLConnection.setRequestProperty("Charset", "UTF-8");
			uRLConnection
					.setRequestProperty("Accept-Encoding", "gzip, deflate");
			uRLConnection.connect();

			DataOutputStream out = new DataOutputStream(
					uRLConnection.getOutputStream());
			out.write(Utility.encodeUrl(param).getBytes());
			out.flush();
			out.close();
			return handleResponse(uRLConnection);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(errorStr, e);
		}
	}

	private String handleResponse(HttpURLConnection httpURLConnection)
			throws CommonException {
		String errorStr = AppMgr.getInstance().getString(R.string.timeout);
		int status = 0;
		try {
			status = httpURLConnection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			httpURLConnection.disconnect();
			throw new CommonException(errorStr, e);
		}

		if (status != HttpURLConnection.HTTP_OK) {
			return handleError(httpURLConnection);
		}

		return readResult(httpURLConnection);
	}

	private String handleError(HttpURLConnection urlConnection)
			throws CommonException {

		String result = readError(urlConnection);
		String err = null;
		int errCode = 0;
		try {
			Log.e("error=" + result, err);
			JSONObject json = new JSONObject(result);
			err = json.optString("error_description", "");
			if (TextUtils.isEmpty(err)) {
				err = json.getString("error");
			}
			errCode = json.getInt("error_code");
			CommonException exception = new CommonException();
			exception.setError_code(errCode);
			throw exception;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

	private String readResult(HttpURLConnection urlConnection)
			throws CommonException {
		InputStream is = null;
		BufferedReader buffer = null;
		String errorStr = AppMgr.getInstance().getString(R.string.timeout);
		try {
			is = urlConnection.getInputStream();

			String content_encode = urlConnection.getContentEncoding();

			if (!TextUtils.isEmpty(content_encode)
					&& content_encode.equals("gzip")) {
				is = new GZIPInputStream(is);
			}

			buffer = new BufferedReader(new InputStreamReader(is));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			while ((line = buffer.readLine()) != null) {
				strBuilder.append(line);
			}
			// AppLogger.d("result=" + strBuilder.toString());
			return strBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(errorStr, e);
		} finally {
			closeSilently(is);
			closeSilently(buffer);
			urlConnection.disconnect();
		}
	}

	private String readError(HttpURLConnection urlConnection)
			throws CommonException {
		InputStream is = null;
		BufferedReader buffer = null;
		String errorStr = AppMgr.getInstance().getString(R.string.timeout);

		try {
			is = urlConnection.getErrorStream();

			if (is == null) {
				errorStr = AppMgr.getInstance().getString(
						R.string.unknown_network_error);
				throw new CommonException(errorStr);
			}

			String content_encode = urlConnection.getContentEncoding();

			if (!TextUtils.isEmpty(content_encode)
					&& content_encode.equals("gzip")) {
				is = new GZIPInputStream(is);
			}

			buffer = new BufferedReader(new InputStreamReader(is));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			while ((line = buffer.readLine()) != null) {
				strBuilder.append(line);
			}
			Log.d(TAG, "error result=" + strBuilder.toString());
			return strBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(errorStr, e);
		} finally {
			closeSilently(is);
			closeSilently(buffer);
			urlConnection.disconnect();
		}
	}

	public String doGet(String urlStr, Map<String, String> param)
			throws CommonException {
		String errorStr = AppMgr.getInstance().getString(R.string.timeout);
		InputStream is = null;
		try {

			StringBuilder urlBuilder = new StringBuilder(urlStr);
			urlBuilder.append("?").append(Utility.encodeUrl(param));
			URL url = new URL(urlBuilder.toString());
			Log.d(TAG, "get request" + url);
			Proxy proxy = getProxy();
			HttpURLConnection urlConnection;
			if (proxy != null) {
				urlConnection = (HttpURLConnection) url.openConnection(proxy);
			} else {
				urlConnection = (HttpURLConnection) url.openConnection();
			}

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(false);
			urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
			urlConnection.setReadTimeout(READ_TIMEOUT);
			urlConnection.setRequestProperty("Connection", "Keep-Alive");
			urlConnection.setRequestProperty("Charset", "UTF-8");
			urlConnection
					.setRequestProperty("Accept-Encoding", "gzip, deflate");

			urlConnection.connect();

			return handleResponse(urlConnection);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommonException(errorStr, e);
		}
	}
	
	public void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {

            }
        }
    }
}
