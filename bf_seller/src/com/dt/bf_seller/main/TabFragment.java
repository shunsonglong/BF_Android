package com.dt.bf_seller.main;

import com.dt.bf_seller.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TabFragment extends Fragment implements View.OnClickListener {
	private LinearLayout mMyShop;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main_tab, container,
				false);
		mMyShop = (LinearLayout)view.findViewById(R.id.item_shop);
		mMyShop.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {

		case R.id.item_shop:
			startShopActivity();
			break;
		}
	}

	public void startShopActivity() {
		Intent intent = new Intent(getActivity(), ShopActivity.class);
		startActivity(intent);
	}
}
