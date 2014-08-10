package com.dt.bf_seller.setup;

import com.dt.bf_seller.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class InputPassWord extends Activity {
	private EditText inputPW;
	private EditText inputPW2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inputpassword);
		inputPW = (EditText) findViewById(R.id.inputpassword);
		inputPW2 = (EditText) findViewById(R.id.inputpassword2);

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
			break;
		case R.id.action_next:
			register();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void register() {
		String PW = inputPW.getText().toString();
		String PW2 = inputPW2.getText().toString();
		if(PW.length() == 0 || PW2.length() == 0) {
			Toast.makeText(this, R.string.password_is_null, Toast.LENGTH_SHORT).show();
		} else if(!PW.equals(PW2)) {
			Toast.makeText(this, R.string.password_not_same, Toast.LENGTH_SHORT).show();
		} else {

		}
	}
}
