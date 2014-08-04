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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private static final String TAG = "LoginActivity";
	private EditText loginaccount, loginpassword;
	private Button loginBtn;
	//private TextView register;
	private Intent mIntent;
	private String getpassword, getaccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
	}

	protected void initView() {

		loginaccount = (EditText) this.findViewById(R.id.loginaccount);
		loginpassword = (EditText) this.findViewById(R.id.loginpassword);

		loginBtn = (Button) this.findViewById(R.id.login);
		// register = (TextView) this.findViewById(R.id.register);

		loginBtn.setOnClickListener(this);
		// register.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_register:
			register();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//case R.id.register:
		//	register();
		//	break;

		case R.id.login:
			login();
			break;

		default:
			break;
		}

	}

	public void register() {
		mIntent = new Intent(LoginActivity.this, RegisterInputPNActivity.class);
		startActivity(mIntent);
	}

	@SuppressLint("ShowToast")
	public void login() {
		if (loginaccount.getText().length() == 0) {
			Toast.makeText(this, R.string.account_is_null, Toast.LENGTH_SHORT).show();
		} else if (loginpassword.getText().length() == 0) {
			Toast.makeText(this, R.string.password_is_null, Toast.LENGTH_SHORT).show();
		} else {
			new LoginTask().execute();
		}
	}

	public String getUrl() {
		return URLHelper.LOGIN_URL;
	}

	private class LoginTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				getpassword = loginpassword.getText().toString();
				getaccount = loginaccount.getText().toString();

				Log.d(TAG, "account = " + getaccount + "password = "
						+ getpassword);

				String url = getUrl();

				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				post.addHeader("Content-Type", "application/json;charset=UTF-8");
				JSONObject obj = new JSONObject();
				obj.put(Constants.CUSTOMER_TYPE, 0);
				obj.put(Constants.CUSTOMER_NAME, getaccount);
				obj.put(Constants.CUSTOMER_LOGIN_PW, getpassword);
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
				finish();
			} else {
				Log.d(TAG, "error");

			}

			super.onPostExecute(result);
		}
	}
}
