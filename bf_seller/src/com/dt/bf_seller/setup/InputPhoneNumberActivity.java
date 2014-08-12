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

public class InputPhoneNumberActivity extends Activity {
	private static final String TAG = "InputPhoneNumber";
	private EditText mPhoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inputphonenumber);
		mPhoneNumber = (EditText) findViewById(R.id.inputphonenumber);
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
			return true;
		case R.id.action_next:
			requestSecurityCode();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void startNextActivity() {
		Intent intent = new Intent(InputPhoneNumberActivity.this,
		VerifySecurityCodeActivity.class);
		String phoneNumber = mPhoneNumber.getText().toString();
		intent.putExtra("phonenumber", phoneNumber);
		startActivity(intent);
	}

	public void requestSecurityCode() {
		if (mPhoneNumber.getText().length() > 0) {
			new RequestSecurityCodeTask().execute();
		} else {
			Toast.makeText(this, R.string.phone_number_is_null,
					Toast.LENGTH_SHORT).show();
		}
		
	}

	public String getUrl() {
		return URLHelper.REGISTER_SECURITY_CODE;
	}

	private class RequestSecurityCodeTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				String phoneNumber = mPhoneNumber.getText().toString();

				Log.d(TAG, "phoneNumber = " + phoneNumber);
				String url = getUrl();

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
				startNextActivity();
			} else {
				Log.d(TAG, "error");
				Toast.makeText(InputPhoneNumberActivity.this, "error",
						Toast.LENGTH_SHORT).show();
				startNextActivity(); //for test
			}

			super.onPostExecute(result);
		}
	}
}
