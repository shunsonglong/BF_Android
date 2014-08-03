package com.dt.zero.network;

import java.util.Map;

import com.dt.zero.util.CommonException;

public class HttpUtility {

	private static HttpUtility httpUtility = new HttpUtility();

	private HttpUtility() {
	}

	public static HttpUtility getInstance() {
		return httpUtility;
	}

	public String executeNormalTask(HttpMethod httpMethod, String url,
			Map<String, String> param) throws CommonException {
		return new JavaHttpUtility().executeNormalTask(httpMethod, url, param);
	}

}
