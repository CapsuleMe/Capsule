package com.capsule.common;

import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferencesEditor {

	public static String SettingsName = "settings";
	
	private Context myContext = null;
	private SharedPreferences preferences = null; 
	
	public SharePreferencesEditor(Context context,String name)
	{
		myContext = context;
		preferences = myContext.getSharedPreferences(name, Context.MODE_PRIVATE);
	}
	
	public void put(String key,int value)
	{
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public void put(String key,String value)
	{
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public void put(String key,Set<String> value)
	{
		Editor editor = preferences.edit();
		editor.putStringSet(key, value);
		editor.commit();
	}
	
	public int get(String key,int defValue)
	{
		return preferences.getInt(key, defValue);
	}
	
	public String get(String key,String defValue)
	{
		return preferences.getString(key, defValue);
	}
	
	public Set<String> get(String key,Set<String> defValue)
	{
		return preferences.getStringSet(key, defValue);
	}
	

	
}
