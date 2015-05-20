package com.dt.bf_seller.main;

import java.util.ArrayList;
import java.util.List;

import com.dt.bf_seller.R;
import com.dt.bf_seller.adapter.MainViewPagerAdatper;
import com.dt.bf_seller.setup.InputPhoneNumberActivity;
import com.dt.bf_seller.setup.VerifySecurityCodeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener {
	private ViewPager mViewPager;
	private ImageView mMessageImg;
	private ImageView mSettingImg;
	private List<Fragment> mFragmentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
	}

	public void initView() {
		mMessageImg = (ImageView) findViewById(R.id.message);
		mMessageImg.setOnClickListener(this);
		mSettingImg = (ImageView) findViewById(R.id.setting);
		mSettingImg.setOnClickListener(this);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		mFragmentList = new ArrayList<Fragment>();
		Fragment mFragment = new TabFragment();
		mFragmentList.add(mFragment);

		MainViewPagerAdatper mAdapter = new MainViewPagerAdatper(
				getSupportFragmentManager(), mFragmentList);
		mViewPager.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.message:
			startMessageActivity();
			break;
		case R.id.setting:
			startSettingActivity();
			break;
		}
	}

	public void startSettingActivity() {

	}

	public void startMessageActivity() {

	}
}
