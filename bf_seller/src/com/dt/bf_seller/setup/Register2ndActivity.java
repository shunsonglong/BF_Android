package com.dt.bf_seller.setup;

import java.util.Timer;
import java.util.TimerTask;

import com.dt.bf_seller.R;
import com.dt.bf_seller.app.AppMgr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register2ndActivity extends Activity {
	private TextView mPhoneNumber;
	private Button mGetSecuritycode;
	private Timer mTimer;
	private int count;
	
	private final int MSG_REFREASH_BUTTON = 1;
	private final int MSG_ENABLE_BUTTON = 2;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_REFREASH_BUTTON:
				String tmp = (60-count) + AppMgr.getInstance().getString(R.string.waiting_get_security_code);
				mGetSecuritycode.setText(tmp);
				mGetSecuritycode.setEnabled(false);
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
		setContentView(R.layout.activity_register2nd);
		mPhoneNumber = (TextView) findViewById(R.id.phonenumber);
		Intent intent = getIntent();
		String phoneNumber = intent.getStringExtra("phonenumber");
		mPhoneNumber.setText(phoneNumber);
		mGetSecuritycode = (Button) findViewById(R.id.getsecritycode);
		mGetSecuritycode.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub

			}
		});
		mGetSecuritycode.setEnabled(false);
		startTimer();
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
			sendRequest();
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

	public void sendRequest() {

	}

}
