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

public class Register1stActivity extends Activity {
	private EditText mPhoneNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register1st);
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
				sendRequest();
				return true;
			} else {
				Toast.makeText(this, R.string.phone_number_is_null, Toast.LENGTH_SHORT).show();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendRequest() {
		Intent intent = new Intent(Register1stActivity.this, Register2ndActivity.class);
		String phoneNumber = mPhoneNumber.getText().toString();
		intent.putExtra("phonenumber", phoneNumber);
		startActivity(intent);
	}
}
