package com.dt.bf_seller.util;

import android.os.Environment;


public class Constants {
	/**
	 ******************************************* 参数设置信息******************************************
	 */

	// 应用名称
	public static String APP_NAME = "";

	// 保存参数文件夹名
	public static final String SHARED_PREFERENCE_NAME = "ele_prefs";

	// SDCard路径
	public static final String SD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();

	// 图片存储路径
	public static final String BASE_PATH = SD_PATH + "/ele/";

	// 缓存图片路径
	public static final String BASE_IMAGE_CACHE = BASE_PATH + "cache/images/";
	
	/**
	 ******************************************* 网络相关******************************************
	 */
	
	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int SO_TIMEOUT = 10000;

	/**
	 ******************************************* 数据库字段 ******************************************
	 */
	public static final String REGISTER_MOBILE = "Mobile"; //register
	public static final String REGISTER_SECURITY_CODE = "Code";
	
	public static final String CUSTOMER_TYPE = "CustomerType";
	public static final String CUSTOMER_PHONE = "CustomerPhone";
	public static final String CUSTOMER_NAME = "CustomerName";
	public static final String CUSTOMER_LOGIN_PW = "CustomerLoginPW";
}
