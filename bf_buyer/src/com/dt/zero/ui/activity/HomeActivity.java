package com.dt.zero.ui.activity;

import com.dt.zero.R;
import com.dt.zero.fragment.HomeFragment;
import com.dt.zero.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

@SuppressLint("NewApi") public class HomeActivity extends SlidingActivity {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.frame_content);
		
		setBehindContentView(R.layout.frame_menu);
		
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.slidingpane_menu, getMenuFragment(), MenuFragment.class.getName());
        fragmentTransaction.replace(R.id.slidingpane_content, getHomeFragment(), MenuFragment.class.getName());
        fragmentTransaction.commit();
 
        // customize the SlidingMenu
        SlidingMenu sm = getSlidingMenu();
        sm.setShadowWidth(50);
        sm.setShadowDrawable(R.drawable.slidemenu_shadow);
        sm.setBehindOffset(150);
        sm.setFadeDegree(0.35f);
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); 
	}
	
	public MenuFragment getMenuFragment() {
		MenuFragment mf = (MenuFragment) getFragmentManager().findFragmentByTag(MenuFragment.class.getName());
		if(mf == null) {
			mf = MenuFragment.newInstance();
		}
		return mf;
	}
	
	public HomeFragment getHomeFragment() {
		HomeFragment mf = (HomeFragment) getFragmentManager().findFragmentByTag(HomeFragment.class.getName());
		if(mf == null) {
			mf = HomeFragment.newInstance();
		}
		return mf;
	}

}
