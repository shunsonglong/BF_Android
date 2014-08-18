package com.dt.bf_seller.setup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.dt.bf_seller.R;
import com.dt.bf_seller.main.MainActivity;
import com.dt.bf_seller.util.Constants;
import com.dt.bf_seller.util.URLHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private static String TAG = "LoginActivity";
	private EditText inputPN;
	private EditText inputPW;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		inputPN = (EditText) findViewById(R.id.inputphonenumber);
		inputPW = (EditText) findViewById(R.id.inputpassword);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_next:
			login();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void login() {
		String PhoneNumber = inputPN.getText().toString();
		String PW = inputPW.getText().toString();

		if (PhoneNumber.length() == 0) {
			Toast.makeText(this, R.string.phone_number_is_null,
					Toast.LENGTH_SHORT).show();
		} else if (PW.length() == 0) {
			Toast.makeText(this, R.string.password_is_null, Toast.LENGTH_SHORT)
					.show();
		} else {
			startMainActivity();
			//new loginTask().execute();
		}
	}

	public void startMainActivity() {
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);
		Intent intent1 = new Intent();
		intent1.putExtra("result", "success");
		setResult(SetupActivity.START_ACTIVITY_FOR_LOGIN, intent1);
		finish();
	}

	public String getRegisterURL() {
		return URLHelper.LOGIN_URL;
	}

	private class loginTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				String phoneNumber = inputPN.getText().toString();
				String pw = inputPW.getText().toString();

				Log.d(TAG, "PhoneNumber = " + phoneNumber + "PW = " + pw);
				String url = getRegisterURL();

				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(
						CoreConnectionPNames.CONNECTION_TIMEOUT,
						Constants.CONNECTION_TIMEOUT);
				client.getParams().setParameter(
						CoreConnectionPNames.SO_TIMEOUT, Constants.SO_TIMEOUT);
				HttpPost post = new HttpPost(url);
				post.addHeader("Content-Type", "application/json;charset=UTF-8");
				JSONObject obj = new JSONObject();
				obj.put(Constants.CUSTOMER_TYPE, 1);
				obj.put(Constants.CUSTOMER_PHONE, phoneNumber);
				obj.put(Constants.CUSTOMER_LOGIN_PW, pw);
				post.setEntity(new StringEntity(obj.toString()));
				HttpResponse response = client.execute(post);

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String rev = EntityUtils.toString(response.getEntity());// 返回json格式
					obj = new JSONObject(rev);
					String result = obj.getString("success");
					Log.d(TAG, "result = " + result);
					Log.d(TAG, "rev = " + rev);
					return result;
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, "JSONException");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, "UnsupportedEncodingException");
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, "ClientProtocolException");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, "ClientProtocolException");
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null && "true".equals(result)) {
				Log.d(TAG, "result = " + result);
				startMainActivity();
				Intent intent = new Intent();
				intent.putExtra("result", "success");
				setResult(SetupActivity.START_ACTIVITY_FOR_LOGIN, intent);
				finish();
			} else {
				Log.d(TAG, "error");
				Toast.makeText(LoginActivity.this, "login fail",
						Toast.LENGTH_SHORT).show();
			}

			super.onPostExecute(result);
		}
	}
}
