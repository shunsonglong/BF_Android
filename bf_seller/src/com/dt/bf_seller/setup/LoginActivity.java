package com.dt.bf_seller.setup;

import com.dt.bf_seller.R;
import com.dt.bf_seller.setup.InputPhoneNumberActivity.RequestSecurityCodeTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
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
		String PW = inputPN.getText().toString();
		
		if (PhoneNumber.length() == 0) {
			Toast.makeText(this, R.string.phone_number_is_null,
					Toast.LENGTH_SHORT).show();
		} else if(PW.length() == 0) {
			Toast.makeText(this, R.string.password_is_null,
					Toast.LENGTH_SHORT).show();
		} else {
			
		}
	}
}
