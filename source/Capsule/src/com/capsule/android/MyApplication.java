package com.capsule.android;

import greendroid.app.GDApplication;
import android.content.Context;

import com.capsule.android.cache.FriendManager;
import com.capsule.android.cache.UserCache;

public class MyApplication extends GDApplication {

	public static Context CONTEXT = null;
	
	public FriendManager fManager = null;

	public UserCache userCache = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CONTEXT = this;
		
		loadData();
	}

	private void loadData(){
		fManager = new FriendManager();
		fManager.load();
		
		userCache = new UserCache();
	}
}
