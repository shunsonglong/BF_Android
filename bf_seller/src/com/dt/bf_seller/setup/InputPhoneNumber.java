package com.dt.bf_seller.setup;

import com.dt.bf_seller.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class InputPhoneNumber extends Activity {
	private EditText mPhoneNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inputphonenumber);
		mPhoneNumber = (EditText)findViewById(R.id.inputphonenumber);
		final ActionBar actionBar = getActionBar();
	    if(actionBar != null) {
	    	//actionBar.setDisplayHomeAsUpEnabled(true);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.register_input_phonenumber, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.action_next:
			if(mPhoneNumber.getText().length() > 0) {
				requestSecurityCode();
				return true;
			} else {
				Toast.makeText(this, R.string.phone_number_is_null, Toast.LENGTH_SHORT).show();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void requestSecurityCode() {
		Intent intent = new Intent(InputPhoneNumber.this, RequestSecurityCode.class);
		String phoneNumber = mPhoneNumber.getText().toString();
		intent.putExtra("phonenumber", phoneNumber);
		startActivity(intent);
	}
}
