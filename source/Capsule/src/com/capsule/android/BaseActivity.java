package com.capsule.android;

import android.app.Activity;
import android.os.Bundle;

import com.capsule.common.Navigator;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myNavigator = new Navigator(this);
	}

	protected Navigator myNavigator = null;

}
