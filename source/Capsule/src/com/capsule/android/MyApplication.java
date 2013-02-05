package com.capsule.android;

import android.content.Context;

import com.capsule.android.cache.FriendManager;

import greendroid.app.GDApplication;

public class MyApplication extends GDApplication {

	public static Context CONTEXT = null;
	
	public FriendManager fManager = null;

	
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
	}
}
