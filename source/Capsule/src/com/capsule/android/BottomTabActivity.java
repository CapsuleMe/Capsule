package com.capsule.android;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.capsule.android.service.LocationService;
import com.capsule.android.widget.MenuBarLayout;
import com.capsule.android.widget.OnMenuBarSelectListener;


@SuppressWarnings("deprecation")
public class BottomTabActivity extends ActivityGroup {
	  
	
	static BottomTabActivity instance = null;

	LocalActivityManager manager;
	MenuBarLayout menuBar = null;
	int currentTabIndex;
	ViewGroup container = null;
	ArrayList<Intent> viewList = null;
	
	public static BottomTabActivity getInstance(){
		return instance;
	}

	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_bottomtab);

	    instance = this;
	    viewList = new ArrayList<Intent>();
	    manager=getLocalActivityManager();  
	    container = (ViewGroup)this.findViewById(R.id.container);
	    
	    startLocationService();
	    
	    
	    initialViews();
	    initialMenuBar();

	    currentTabIndex = 1;
	}

	@Override
	public void onResume(){
		super.onResume();
		
		setCurrentView(currentTabIndex);
	}

	public void setCurrentView(int index){
		  if(index<0 || index >= viewList.size())
			  return;
	   
		   currentTabIndex = index;
		   container.removeAllViews();
		   container.addView(getCurrentView(index));
		   
		   menuBar.setButtonSelected(index, true);
	}
	
	private View getCurrentView(int index)
	{
		return manager.startActivity(
				index+"",
				viewList
				.get(index)
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
				.getDecorView();
	}
	
	
	/*
	 *  Tab
	 */	
	private void initialViews(){	
		viewList.add(new Intent(this, MessageActivity.class));
		viewList.add(new Intent(this, FriendActivity.class));
		viewList.add(new Intent(this, CapSuleMapActivity.class));
		viewList.add(new Intent(this, NamecardActivity.class));
		viewList.add(new Intent(this, SettingActivity.class));
		viewList.add(new Intent(this, CardSettingActivity.class));
		  
	} 

	private void initialMenuBar(){
		  menuBar = (MenuBarLayout)this.findViewById(R.id.menubar);
		  menuBar.setOnMenuBarSelectListener(new OnMenuBarSelectListener(){

				public void onSelected(int index, View v) {
					setCurrentView(index);
				}}); 
	}

	 /**Start Location Service*/
    private void startLocationService(){
            Intent intent = new Intent(this, LocationService.class);
            this.startService(intent);  
    }


}