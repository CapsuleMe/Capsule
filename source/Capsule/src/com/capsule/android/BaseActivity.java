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
	}

	protected Navigator myNavigator = null;

}
