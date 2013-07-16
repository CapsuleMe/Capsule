package com.capsule.android;

import greendroid.app.GDApplication;
import android.content.Context;

import com.capsule.android.cache.CacheFactory;
import com.capsule.android.rest.RestFactory;

public class MyApplication extends GDApplication {

	public static Context CONTEXT = null;
	public CacheFactory cache = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		CONTEXT = this;
		
		//RestFactory.BaseUrl = "http://10.200.52.62:3000";
		RestFactory.BaseUrl = "http://10.0.2.2:3000/";
		
		CacheData();
	}

	private void CacheData(){
		
		cache = new CacheFactory();
		cache.load();
	}
}
