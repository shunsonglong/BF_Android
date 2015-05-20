package com.dt.bf_seller.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MainViewPagerAdatper extends FragmentPagerAdapter {

	private List<Fragment> mPagerViews;

	public MainViewPagerAdatper(FragmentManager fragmentManager, List<Fragment> pagerViews) {
		// TODO Auto-generated constructor stub
		super(fragmentManager);
		this.mPagerViews = pagerViews;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mPagerViews.size();
	}

	@Override
	public Fragment getItem(int pos) {
		// TODO Auto-generated method stub
		return mPagerViews.get(pos);
	}

}
