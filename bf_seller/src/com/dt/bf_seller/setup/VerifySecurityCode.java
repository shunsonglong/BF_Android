package com.dt.bf_seller.setup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

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
import com.dt.bf_seller.app.AppMgr;
import com.dt.bf_seller.util.Constants;
import com.dt.bf_seller.util.URLHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerifySecurityCode extends Activity {
	private static String TAG = "Register2ndActivity";
	private TextView mPhoneNumber;
	private EditText mSecurityEditText;
	private Button mGetSecuritycode;
	private Timer mTimer;
	private int count;
	private String phoneNumber;
	
	private final int MSG_REFREASH_BUTTON = 1;
	private final int MSG_ENABLE_BUTTON = 2;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_REFREASH_BUTTON:
				String tmp = (60-count) + AppMgr.getInstance().getString(R.string.waiting_get_security_code);
				mGetSecuritycode.setText(tmp);
				break;
			case MSG_ENABLE_BUTTON:
				if (mTimer != null) {
					mTimer.cancel();
				}
				mGetSecuritycode.setText(R.string.reget_security_code);
				mGetSecuritycode.setEnabled(true);
				count = 0;
				break;
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requestsecuritycode);
		mPhoneNumber = (TextView) findViewById(R.id.phonenumber);
		mSecurityEditText = (EditText) findViewById(R.id.inputsecuritycode);
		Intent intent = getIntent();
		phoneNumber = intent.getStringExtra("phonenumber");
		mPhoneNumber.setText(phoneNumber);
		mGetSecuritycode = (Button) findViewById(R.id.getsecritycode);
		mGetSecuritycode.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Log.d(TAG, "Button onclick");
				requestSecurityCode();
			}
		});
		mGetSecuritycode.setEnabled(false);
		startTimer();
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
			VerifyCode();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void startTimer() {
		if (mTimer != null) {
			mTimer.cancel();  
		}
		mTimer = new Timer();
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				count++;
				if(count == 60) {
					Message msg = new Message();
					msg.what = MSG_ENABLE_BUTTON;
					mHandler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = MSG_REFREASH_BUTTON;
					mHandler.sendMessage(msg);
				}
			}
		}, 1000, 1000);
	}

	public void startNextActivity() {
		Intent intent = new Intent(VerifySecurityCode.this, InputPassWord.class);
		intent.putExtra("phonenumber", phoneNumber);
		startActivity(intent);
	}
	
	public void VerifyCode() {
		new VerifySecurityCodeTask().execute();
	}
	
	public void requestSecurityCode() {
		new RequestSecurityCodeTask().execute();
	}

	public String getRequestSecurityCodeUrl() {
		return URLHelper.REGISTER_SECURITY_CODE;
	}
	
	public String getVerifySecurityCodeUrl() {
		return URLHelper.REGISTER_SECURITY_CODE_IN;
	}

	private class RequestSecurityCodeTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {

				Log.d(TAG, "phoneNumber = " + phoneNumber);
				String url = getRequestSecurityCodeUrl();

				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(
						CoreConnectionPNames.CONNECTION_TIMEOUT, Constants.CONNECTION_TIMEOUT);
				client.getParams().setParameter(
						CoreConnectionPNames.SO_TIMEOUT, Constants.SO_TIMEOUT);
				HttpPost post = new HttpPost(url);
				post.addHeader("Content-Type", "application/json;charset=UTF-8");
				JSONObject obj = new JSONObject();
				obj.put(Constants.CUSTOMER_TYPE, 1);
				obj.put(Constants.REGISTER_MOBILE, phoneNumber);
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
			} else {
				Log.d(TAG, "error");
			}

			super.onPostExecute(result);
		}
	}
	
	private class VerifySecurityCodeTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				Log.d(TAG, "phoneNumber = " + phoneNumber);
				String securityCode = mSecurityEditText.getText().toString();
				Log.d(TAG, "securityCode = " + securityCode);
				String url = getVerifySecurityCodeUrl();

				HttpClient client = new DefaultHttpClient();
				client.getParams().setParameter(
						CoreConnectionPNames.CONNECTION_TIMEOUT, Constants.CONNECTION_TIMEOUT);
				client.getParams().setParameter(
						CoreConnectionPNames.SO_TIMEOUT, Constants.SO_TIMEOUT);
				HttpPost post = new HttpPost(url);
				post.addHeader("Content-Type", "application/json;charset=UTF-8");
				JSONObject obj = new JSONObject();
				obj.put(Constants.CUSTOMER_TYPE, 1);
				obj.put(Constants.REGISTER_MOBILE, phoneNumber);
				obj.put(Constants.REGISTER_SECURITY_CODE, securityCode);
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
				startNextActivity();
			} else {
				Log.d(TAG, "error");
				Toast.makeText(VerifySecurityCode.this, "error",
						Toast.LENGTH_SHORT).show();
			}

			super.onPostExecute(result);
		}
	}

}
