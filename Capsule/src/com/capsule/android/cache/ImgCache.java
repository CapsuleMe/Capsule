package com.capsule.android.cache;

import android.graphics.drawable.Drawable;

import com.capsule.android.MyApplication;
import com.capsule.android.R;

public class ImgCache {

	private static ImgCache instance = null;
	
	public static ImgCache getInstance(){
		if(instance ==null)
			instance= new ImgCache();
		
		return instance;
	}
	
	private Drawable default_face = null;	
	
	public ImgCache(){
		default_face = MyApplication.CONTEXT.getResources().getDrawable(R.drawable.default_face);
	}
	
	public Drawable getDefaultFace(){
		return default_face;
	}
}
