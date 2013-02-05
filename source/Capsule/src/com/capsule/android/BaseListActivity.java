package com.capsule.android;

import com.capsule.common.Navigator;

import android.app.ListActivity;
import android.os.Bundle;

public class BaseListActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myNavigator = new Navigator(this);
	}

	protected Navigator myNavigator = null;
}
