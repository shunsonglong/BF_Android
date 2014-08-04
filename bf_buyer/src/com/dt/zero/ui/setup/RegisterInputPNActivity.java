package com.dt.zero.ui.setup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.dt.zero.R;
import com.dt.zero.util.Constants;
import com.dt.zero.util.URLHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterInputPNActivity extends Activity {
	private static String TAG = "RegisterInputPNActivity";
	private Button sendButton;
	private EditText phoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_inputpn);
		phoneNumber = (EditText) findViewById(R.id.inputphonenumber);
		sendButton = (Button) findViewById(R.id.send);
		sendButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				sendButtonPressed();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendButtonPressed() {
		if(phoneNumber.getText().length() == 0){
			Toast.makeText(this, R.string.account_is_null, Toast.LENGTH_SHORT).show();
		} else {
			new RequestVerifyNumberTask().execute();
		}
	}
	
	public String getUrl() {
		return URLHelper.LOGIN_URL;
	}
	
	private class RequestVerifyNumberTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				String number = phoneNumber.getText().toString();

				Log.d(TAG, "number = " + number);

				String url = getUrl();

				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				post.addHeader("Content-Type", "application/json;charset=UTF-8");
				JSONObject obj = new JSONObject();
				obj.put(Constants.CUSTOMER_TYPE, 0);
				obj.put(Constants.CUSTOMER_PHONE, number);
				post.setEntity(new StringEntity(obj.toString()));
				HttpResponse response = client.execute(post);

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String rev = EntityUtils.toString(response.getEntity());// 返回json格式
					obj = new JSONObject(rev);
					String result = obj.getString("success");
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
				Intent mIntent = new Intent(RegisterInputPNActivity.this, RegisterInputPWActivity.class);
				startActivity(mIntent);
			} else {
				Log.d(TAG, "error");
				Toast.makeText(getApplicationContext(), R.string.unknown_network_error, Toast.LENGTH_SHORT).show();
			}

			super.onPostExecute(result);
		}
	}
}
