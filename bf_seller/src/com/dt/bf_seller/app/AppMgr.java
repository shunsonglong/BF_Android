package com.dt.bf_seller.app;


import android.app.Application;
import android.content.res.Configuration;

public final class AppMgr extends Application {
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

    public static AppMgr getInstance() {
        return instance;
    }

}
