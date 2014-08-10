package com.dt.bf_seller.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import android.text.TextUtils;

public class Utility {
	public static String encodeUrl(Map<String, String> param) {
		if (param == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		Set<String> keys = param.keySet();
		boolean first = true;

		for (String key : keys) {
			String value = param.get(key);

			if (!TextUtils.isEmpty(value) || key.equals("description")
					|| key.equals("url")) {
				if (first) {
					first = false;
				} else {
					sb.append("&");
				}
				try {
					sb.append(URLEncoder.encode(key, "UTF-8")).append("=")
							.append(URLEncoder.encode(param.get(key), "UTF-8"));
				} catch (UnsupportedEncodingException e) {

				}
			}
		}

		return sb.toString();
	}

}
