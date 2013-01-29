package com.capsule.android;

import com.capsule.android.cache.FriendManager;

import greendroid.app.GDApplication;

public class MyApplication extends GDApplication {

	public FriendManager fManager = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		loadData();
		
		
	}

	private void loadData(){
		fManager = new FriendManager();
		fManager.load();
	}
}
