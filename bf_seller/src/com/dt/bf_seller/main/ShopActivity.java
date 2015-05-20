package com.dt.bf_seller.main;

import com.dt.bf_seller.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class ShopActivity extends Activity {
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		initHeaderView();
	}

	public void initHeaderView() {
		mListView = (ListView) findViewById(R.id.listview);
		LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View headerView = lif.inflate(R.layout.shop_header_item, mListView,
				false);

		mListView.addHeaderView(headerView);

		mListView.setAdapter(null);
	}
}
