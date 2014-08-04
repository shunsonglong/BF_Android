package com.dt.zero.fragment;

import com.dt.zero.R;
import com.dt.zero.ui.activity.HomeActivity;
import com.dt.zero.ui.activity.RestaurantActivity;
import com.dt.zero.ui.setup.LoginActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class MenuFragment extends Fragment implements View.OnClickListener {

	private View currentView;
	private ImageView iv_login;
	private LinearLayout bt_home,bt_orders, bt_personal;
	
	public static MenuFragment newInstance() {
		MenuFragment fragment = new MenuFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

	public View getCurrentView() {
		return currentView;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currentView = inflater.inflate(R.layout.slidingpane_menu_layout,
				container, false);
		
		bt_personal = (LinearLayout) currentView.findViewById(R.id.li_personal);
		bt_home = (LinearLayout) currentView.findViewById(R.id.li_home);
		iv_login = (ImageView) currentView.findViewById(R.id.iv_login);
		bt_orders = (LinearLayout) currentView.findViewById(R.id.li_order);
		bt_personal.setOnClickListener(this);
		bt_home.setOnClickListener(this);
		bt_orders.setOnClickListener(this);
		iv_login.setOnClickListener(this);
		return currentView;
	}

	@SuppressLint("CommitTransaction")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();// 开始一个事物
		switch (v.getId()) {
		case R.id.li_personal:
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.li_home:
			Fragment homeFragment = new HomeFragment();
			ft.replace(R.id.slidingpane_content, homeFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		case R.id.li_order:
			Fragment orderFragment = new OrderFragment();
			ft.replace(R.id.slidingpane_content, orderFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			ft.commit();
			break;
		}
		((HomeActivity)getActivity()).getSlidingMenu().toggle();
	}
}
