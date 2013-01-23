package com.capsule.android;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.capsule.android.widget.MenuBarLayout;
import com.capsule.android.widget.OnMenuBarSelectListener;


@SuppressWarnings("deprecation")
public class BottomTabActivity extends TabActivity {

	static BottomTabActivity instance = null;

	View lastView = null;// For animation;
	TabHost tabHost = null;
	MenuBarLayout menuBar = null;
	int currentTabIndex,lastTabIndex;

	public static BottomTabActivity getInstance(){
		return instance;
	}

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_bottomtab);

	    instance = this;
	    initialTabs();
	    initialMenuBar();

	    currentTabIndex = 1;

	}

	@Override
	public void onResume(){
		super.onResume();
		
		setCurrentTab(currentTabIndex);
	}

	/*
	 *  Tab
	 */	
	private void initialTabs(){
		  tabHost = getTabHost();  
		  addTab(this,MessageActivity.class,"Message","message");
		  addTab(this,FriendActivity.class,"Friend","friend");
		  addTab(this,CapSuleMapActivity.class,"Map","map");
		  addTab(this,NamecardActivity.class,"Namecard","namecard");
		  addTab(this,SettingActivity.class,"Setting","setting");
		  
		  tabHost.setOnTabChangedListener(new OnTabChangeListener(){
				public void onTabChanged(String tabId) {
					if(currentTabIndex > lastTabIndex){
						MoveRightToLeft();
					}else
					{
						MoveLeftToRigt();
					}
				}	
		    });
	} 

	private void initialMenuBar(){
		  menuBar = (MenuBarLayout)this.findViewById(R.id.menubar);
		  menuBar.setOnMenuBarSelectListener(new OnMenuBarSelectListener(){

				public void onSelected(int index, View v) {
					setCurrentTab(index);
				}}); 
	}

	public void setCurrentTab(int index){
		  int tabCount = tabHost.getTabWidget().getTabCount();
		  if(index<0 || index >= tabCount)
			  return;
		
		   lastView = tabHost.getCurrentView();
		   lastTabIndex = currentTabIndex;

		   
		   currentTabIndex = index;
		   tabHost.setCurrentTab(index);
		   menuBar.setButtonSelected(index, true);

		   //Is first view
		   if(lastView == null){
			   lastView = tabHost.getCurrentView(); 
		   }
	}

	private void addTab(Context ctx, Class<?> cls,String indicator, String tag) {
		Intent intent = new Intent().setClass(ctx, cls);
		TabHost.TabSpec spec = tabHost.newTabSpec(tag).setIndicator(indicator).setContent(intent);
	    tabHost.addTab(spec);
	}
	
	/*
	 * Animation 
	 */	
	private void MoveLeftToRigt(){
		View currentView = tabHost.getCurrentView();
		 lastView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.out_left_right));
	     currentView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.in_left_right));
	}

	private void MoveRightToLeft(){
		View currentView = tabHost.getCurrentView();
		 lastView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.out_right_left));
	     currentView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.in_right_left));
	}

}