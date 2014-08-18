package com.dt.bf_seller.main;

import java.util.ArrayList;
import java.util.List;

import com.dt.bf_seller.R;
import com.dt.bf_seller.adapter.MainViewPagerAdatper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {
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
		mMessageImg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startMessageActivity();
			}
		});
		mSettingImg = (ImageView) findViewById(R.id.setting);
		mSettingImg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startSettingActivity();
			}
		});
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		mFragmentList = new ArrayList<Fragment>();
		Fragment mFragment = new TabFragment();
		mFragmentList.add(mFragment);

		MainViewPagerAdatper mAdapter = new MainViewPagerAdatper(
				getSupportFragmentManager(), mFragmentList);
		mViewPager.setAdapter(mAdapter);
	}
	
	public void startSettingActivity() {
		
	}
	
	public void startMessageActivity() {
		
	}
}
