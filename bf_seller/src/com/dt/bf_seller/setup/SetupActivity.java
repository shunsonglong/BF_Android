package com.dt.bf_seller.setup;

import com.dt.bf_seller.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SetupActivity extends Activity {
	private Button mLogin;
	private Button mRegister;

	public static int START_ACTIVITY_FOR_REGISTER = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setup);

		mLogin = (Button) findViewById(R.id.login);
		mLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub

			}
		});
		mRegister = (Button) findViewById(R.id.register);
		mRegister.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SetupActivity.this,
						Register1stActivity.class);
				startActivityForResult(intent, START_ACTIVITY_FOR_REGISTER);
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == START_ACTIVITY_FOR_REGISTER) {
			switch (resultCode) {
			case RESULT_OK:
				break;
			}
		}
	}
}