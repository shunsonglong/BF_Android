package com.dt.zero.adapter;

import com.dt.zero.R;
import com.dt.zero.bean.FoodBean;
import com.dt.zero.util.HashList;
import com.dt.zero.util.KeySort;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class RestaurantRightListAdapter extends SectionedBaseAdapter {
	
	private Context mContext;
	private LayoutInflater inflater;
	private HashList<String,FoodBean> mHashList;
	
	public RestaurantRightListAdapter(Context context){
		this.mContext = context;
		inflater=LayoutInflater.from(context);
		init_data();
	}

    @Override
    public Object getItem(int section, int position) {
        return mHashList.getValueIndex(section, position);
    }

    @Override
    public long getItemId(int section, int position) {
        return position;
    }

    @Override
    public int getSectionCount() {
        return mHashList.size();
    }

    @Override
    public int getCountForSection(int section) {
        return mHashList.getValueListIndex(section).size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.restaurant_rightlist_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView
                    .findViewById(R.id.commodity_name);
            holder.logo = (ImageView) convertView
            		.findViewById(R.id.commodity_image);
            holder.rate_numbers = (RatingBar) convertView
            		.findViewById(R.id.commodity_rate);
            holder.buy_nums = (TextView) convertView
            		.findViewById(R.id.commodity_buynums);
            convertView.setTag(holder);
            
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        
        FoodBean mFood = (FoodBean) getItem(section, position);
        holder.name.setText(mFood.getName());
        holder.rate_numbers.setNumStars(mFood.getRate_numbers());
        holder.buy_nums.setText(mFood.getBuy_nums());
        
        ImageLoader.getInstance().displayImage(mFood.getLogo(), holder.logo);
		
		return convertView;
	}

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.restaurant_leftlist_item, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        FoodBean mFood = (FoodBean) getItem(section, 0);
        ((TextView) layout.findViewById(R.id.textItem)).setText(mFood.getType());
        return layout;
    }

    public class ViewHolder {
		public ImageView logo;
		public TextView name;
		public RatingBar rate_numbers;
		public TextView buy_nums;
	}
    
    public void init_data() {

    	mHashList=new HashList<String,FoodBean>(new KeySort<String,FoodBean>(){
			@Override
			public String getKey(FoodBean v) {
				return v.getType();
			}});
		FoodBean food1 = new FoodBean();
		food1.setLogo("drawable://" + R.drawable.pic_jigongbao);
		food1.setName("豆浆");
		food1.setType("饮料");
		food1.setRate_numbers(1);
		mHashList.add(food1);

		FoodBean food2 = new FoodBean();
		food2.setLogo("drawable://" + R.drawable.pic_jixiang);
		food2.setName("咖啡");
		food2.setType("饮料");
		food2.setRate_numbers(1);
		mHashList.add(food2);

		FoodBean food3 = new FoodBean();
		food3.setLogo("drawable://" + R.drawable.pic_milishi);
		food3.setName("包子");
		food3.setType("主食");
		food3.setRate_numbers(2);
		mHashList.add(food3);
		
		FoodBean food4 = new FoodBean();
		food4.setLogo("drawable://" + R.drawable.pic_shaxian);
		food4.setName("皮蛋瘦肉粥");
		food4.setType("粥");
		food4.setRate_numbers(2);
		mHashList.add(food4);

	}
}
