package com.capsule.android;

import com.capsule.common.Navigator;
import com.capsule.common.SharePreferencesEditor;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		myNavigator = new Navigator(this);
		mySettingEditor = new SharePreferencesEditor(this,SharePreferencesEditor.SettingsName);
	}

	protected Navigator myNavigator = null;
	protected SharePreferencesEditor mySettingEditor = null; 
}
