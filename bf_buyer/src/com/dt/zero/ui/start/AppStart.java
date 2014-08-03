package com.dt.zero.ui.start;

import com.dt.zero.R;
import com.dt.zero.ui.activity.HomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class AppStart extends Activity {
	@Override  
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_start);
		//延迟1s跳转到主界面
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AppStart.this, HomeActivity.class);
				startActivity(intent);
				AppStart.this.finish();
			}
			
		}, 1000);
	}
}
