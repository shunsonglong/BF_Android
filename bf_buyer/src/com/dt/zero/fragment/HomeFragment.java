package com.dt.zero.fragment;

import java.util.ArrayList;
import java.util.List;

import com.dt.zero.R;
import com.dt.zero.adapter.RestaurantItemAdapter;
import com.dt.zero.bean.RestaurantBean;
import com.dt.zero.ui.activity.RestaurantActivity;
import com.dt.zero.ui.widget.RefreshableListView;
import com.dt.zero.ui.widget.RefreshableListView.OnRefreshListener;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


@SuppressLint("NewApi")
public class HomeFragment extends Fragment {
	private View currentView;
	private RefreshableListView mListView;
	private RestaurantItemAdapter adapter;
	private List<RestaurantBean> mlist;
	private int total = 21;

	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		currentView = inflater.inflate(R.layout.slidingpane_home_layout,
				container, false);
		mListView = (RefreshableListView) currentView
				.findViewById(R.id.mineList);
		getDate();
		setListener();
		return currentView;
	}

	public void setListener() {

		mListView.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh(RefreshableListView listView) {
				new NewDataTask().execute();
			}
		});

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Intent intent = new Intent(getActivity(), RestaurantActivity.class);
				intent.putExtra("name", mlist.get(position+1).getName());
				startActivity(intent);
			}
		});
	}

	private class NewDataTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			int current = mListView.getAdapter().getCount();
			if (current < total) {
				getDate();
			}

			mListView.completeRefreshing();

			super.onPostExecute(result);
		}
	}

	//test data
	private void getDate() {

		mlist = new ArrayList<RestaurantBean>();
		RestaurantBean restaurant1 = new RestaurantBean();
		restaurant1.setLogo("drawable://" + R.drawable.pic_jigongbao);
		restaurant1.setName("齐鲁兄弟鸡公煲");
		restaurant1.setItem_msg("月售208单 / 20元起送 / 30分钟");
		restaurant1.setRate_numbers(1);
		restaurant1.setIs_rest(true);
		//restaurant1.setPromotion("指定食品，每份减4元");
		restaurant1.setIs_half(true);
		restaurant1.setIs_mins(true);
		mlist.add(restaurant1);
		restaurant1 = null;

		RestaurantBean restaurant2 = new RestaurantBean();
		restaurant2.setLogo("drawable://" + R.drawable.pic_jixiang);
		restaurant2.setName("吉祥混沌");
		restaurant2.setItem_msg("月售128单 / 14元起送 / 20分钟");
		//restaurant2.setPromotion("【新】下单立减3元，份份减3，加赠500ml康师傅果汁！");
		restaurant2.setIs_mins(true);
		restaurant2.setRate_numbers(2);
		mlist.add(restaurant2);
		restaurant2 = null;

		RestaurantBean restaurant3 = new RestaurantBean();
		restaurant3.setLogo("drawable://" + R.drawable.pic_milishi);
		restaurant3.setName("迷离士汉堡");
		restaurant3.setItem_msg("月售221单 / 12元起送 / 30分钟");
		restaurant3.setIs_favor(true);
		restaurant3.setRate_numbers(3);
		//restaurant3.setPromotion("【新】赠500ml康师傅果汁！");
		restaurant3.setIs_half(true);
		mlist.add(restaurant3);
		restaurant3 = null; 

		RestaurantBean restaurant4 = new RestaurantBean();
		restaurant4.setLogo("drawable://" + R.drawable.pic_shaxian);
		restaurant4.setName("沙县小吃");
		restaurant4.setItem_msg("月售218单 / 11元起送 / 10分钟");
		restaurant4.setIs_rest(true);
		restaurant4.setRate_numbers(4);
		//restaurant4.setPromotion("帅哥给你送餐！");
		restaurant4.setIs_mins(true);
		mlist.add(restaurant4);
		restaurant4 = null;

		RestaurantBean restaurant5 = new RestaurantBean();
		restaurant5.setLogo("drawable://" + R.drawable.pic_shiguifan);
		restaurant5.setName("韩式石锅饭");
		restaurant5.setItem_msg("月售82单 / 14元起送 / 22分钟");
		restaurant5.setIs_favor(true);
		restaurant5.setRate_numbers(5);
		restaurant5.setIs_mins(true);
		mlist.add(restaurant5);
		restaurant5 = null;

		RestaurantBean restaurant6 = new RestaurantBean();
		restaurant6.setLogo("drawable://" + R.drawable.pic_tengqi);
		restaurant6.setName("藤崎寿司");
		restaurant6.setItem_msg("月售34单 / 11元起送 / 10分钟");
		restaurant6.setRate_numbers(2);
		restaurant6.setIs_mins(true);
		mlist.add(restaurant6);
		restaurant6 = null;

		RestaurantBean restaurant7 = new RestaurantBean();
		restaurant7.setLogo("drawable://" + R.drawable.pic_xiaohongmao);
		restaurant7.setName("小红帽快餐厅");
		restaurant7.setItem_msg("月售233单 / 14元起送 / 20分钟");
		restaurant7.setRate_numbers(3);
		restaurant7.setIs_mins(true);
		mlist.add(restaurant7);
		restaurant7 = null;

		adapter = new RestaurantItemAdapter(getActivity(), mlist);
		mListView.setAdapter(adapter);
	}

}