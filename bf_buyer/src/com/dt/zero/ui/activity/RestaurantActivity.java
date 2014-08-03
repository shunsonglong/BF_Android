package com.dt.zero.ui.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dt.zero.R;
import com.dt.zero.adapter.RestaurantRightListAdapter;
import com.dt.zero.bean.RestaurantBean;
import com.dt.zero.ui.widget.PinnedHeaderListView;

public class RestaurantActivity extends Activity {

	private boolean isScroll = true;

	private ListView left_listView;
	private List<RestaurantBean> mlist;

	private String[] leftStr = new String[] { "饮料", "主食", "粥"};
	private String[][] rightStr = new String[][] {
			{ "豆浆", "咖啡", "茶" },
			{ "八宝粥", "皮蛋瘦肉粥"},
			{ "汉堡", "鸡腿"},
			{ "鸡蛋"}};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_restaurant_detail);
		final PinnedHeaderListView right_listview = (PinnedHeaderListView) findViewById(R.id.right_pinnedListView);

		final RestaurantRightListAdapter sectionedAdapter = new RestaurantRightListAdapter(this);
		right_listview.setAdapter(sectionedAdapter);

		left_listView = (ListView) findViewById(R.id.left_listview);
		left_listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, leftStr));

		left_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				isScroll = false;

				for (int i = 0; i < left_listView.getChildCount(); i++) {
					if (i == position) {
						left_listView.getChildAt(i).setBackgroundColor(
								Color.rgb(255, 255, 255));
					} else {
						left_listView.getChildAt(i).setBackgroundColor(
								Color.TRANSPARENT);
					}
				}

				int rightSection = 0;
				for (int i = 0; i < position; i++) {
					rightSection += sectionedAdapter.getCountForSection(i) + 1;
				}
				right_listview.setSelection(rightSection);

			}

		});

		right_listview.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (isScroll) {
					for (int i = 0; i < left_listView.getChildCount(); i++) {

						if (i == sectionedAdapter
								.getSectionForPosition(firstVisibleItem)) {
							left_listView.getChildAt(i).setBackgroundColor(
									Color.rgb(255, 255, 255));
						} else {
							left_listView.getChildAt(i).setBackgroundColor(
									Color.TRANSPARENT);

						}
					}

				} else {
					isScroll = true;
				}
			}
		});

	}
}
