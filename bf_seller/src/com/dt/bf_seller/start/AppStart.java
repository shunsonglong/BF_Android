package com.dt.bf_seller.start;

import com.dt.bf_seller.R;
import com.dt.bf_seller.setup.SetupActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class AppStart extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AppStart.this, SetupActivity.class);
				startActivity(intent);
				AppStart.this.finish();
			}

		}, 1000);
	}
}
