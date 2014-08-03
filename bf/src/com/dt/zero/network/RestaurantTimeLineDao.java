package com.dt.zero.network;

import java.util.HashMap;
import java.util.Map;

import com.dt.zero.bean.RestaurantBean;
import com.dt.zero.util.CommonException;
import com.dt.zero.util.URLHelper;

public class RestaurantTimeLineDao {
	String longitude; // 经度
	String latitude; // 纬度

	public RestaurantTimeLineDao(String longitude, String latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	private String getUrl() {
		return URLHelper.RESTAURANTIMELINE_URL;
	}

	private String getMsgListJson() throws CommonException {
		String url = getUrl();

		Map<String, String> map = new HashMap<String, String>();
		map.put("longitude", longitude);
		map.put("latitude", latitude);

		String jsonData = HttpUtility.getInstance().executeNormalTask(
				HttpMethod.Get, url, map);
		return jsonData;
	}

	public RestaurantBean getJSONMsgList() throws CommonException {
		RestaurantBean value = null;
		//解析JSON数据
		return value;
	}
}
