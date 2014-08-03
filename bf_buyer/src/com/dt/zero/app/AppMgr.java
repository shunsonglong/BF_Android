package com.dt.zero.app;

import com.dt.zero.util.Constants;
import com.dt.zero.image.ImageLoaderConfig;

import android.app.Application;
import android.content.res.Configuration;

public final class AppMgr extends Application {
	private String jumpType="";
	private static AppMgr instance = null;
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		ImageLoaderConfig.initImageLoader(this, Constants.BASE_IMAGE_CACHE);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

	public String getJumpType() {
		return jumpType;
	}

	public void setJumpType(String jumpType) {
		this.jumpType = jumpType;
	}
	
    public static AppMgr getInstance() {
        return instance;
    }

}
