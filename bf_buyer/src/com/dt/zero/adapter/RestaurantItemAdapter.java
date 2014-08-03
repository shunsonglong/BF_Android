package com.dt.zero.adapter;

import java.util.List;

import com.dt.zero.R;
import com.dt.zero.bean.RestaurantBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class RestaurantItemAdapter extends BaseAdapter{

	private Context mContext;
	private List<RestaurantBean> mlist;
	private LayoutInflater inflater;
	public RestaurantItemAdapter(Context context,
			List<RestaurantBean> list) {
		this.mContext = context;
		this.mlist=list;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mlist.size();
	}

	@Override
	public Object getItem(int position) {
		return mlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.restaurant_list_item_dev, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView
                    .findViewById(R.id.restaurant_list_item_name);
            holder.logo = (ImageView) convertView
            		.findViewById(R.id.restaurant_list_item_logo);
            holder.item_msg = (TextView) convertView
            		.findViewById(R.id.restaurant_list_item_msg);
            holder.buy_nums = (TextView) convertView
            		.findViewById(R.id.restaurant_list_item_buynums);
            holder.rate = (RatingBar) convertView
            		.findViewById(R.id.restaurant_list_item_rate);
            holder.rest = (ImageView) convertView
            		.findViewById(R.id.restaurant_list_item_avaiable);
            holder.favor = (ImageView) convertView
            		.findViewById(R.id.restaurant_list_item_favor);
            holder.promotion = (LinearLayout) convertView
            		.findViewById(R.id.restaurant_list_item_present_promotion_container);
            convertView.setTag(holder);
            
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        if(mlist.get(position).getIs_favor()){
        	holder.favor.setVisibility(View.VISIBLE);
        }
        if(mlist.get(position).getIs_rest()){
        	holder.rest.setVisibility(View.VISIBLE);
        	holder.rate.setVisibility(View.GONE);
        }
        holder.name.setText(mlist.get(position).getName());
        holder.buy_nums.setText(mlist.get(position).getBuy_nums());
        holder.item_msg.setText(mlist.get(position).getItem_msg());
        holder.rate.setNumStars(mlist.get(position).getRate_numbers());

        if(!TextUtils.isEmpty(mlist.get(position).getPromotion())){
        	LinearLayout promotionLayout= (LinearLayout) inflater.inflate(R.layout.restaurant_promotion_with_icon_text_view, null);
        	TextView promotionTv = (TextView) promotionLayout.findViewById(R.id.restaurant_promotion_description);
        	TextView promotionIcon = (TextView) promotionLayout.findViewById(R.id.restaurant_promotion_icon);
        	if(mlist.get(position).getIs_half()){
        		promotionIcon.setText("on");
        	}
        	if(mlist.get(position).getIs_mins()){
        		promotionIcon.setText("off");
        	}
        	promotionTv.setText(mlist.get(position).getPromotion());
        	holder.promotion.addView(promotionLayout);
        }
        
        ImageLoader.getInstance().displayImage(mlist.get(position).getLogo(), holder.logo);
		
		return convertView;
	}
	public class ViewHolder {
		public ImageView logo;
		public TextView name;
		public TextView rate_numbers;
		public TextView buy_nums;
		public TextView item_msg;
		public ImageView rest;
		public ImageView favor;
		public RatingBar rate;
		public LinearLayout promotion;
		
	}

}
