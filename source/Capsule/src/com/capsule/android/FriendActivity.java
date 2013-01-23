package com.capsule.android;

import greendroid.widget.ItemAdapter;
import greendroid.widget.item.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.capsule.android.widget.item.FriendItem;
import com.capsule.common.Navigator;
import com.capsule.model.Friend;
import com.capsule.model.Location;
import com.capsule.model.User;



public class FriendActivity extends BaseListActivity {

	ItemAdapter adapter  = null;
	LinearLayout searchBar = null;
	EditText searchText = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        
        searchText = (EditText)this.findViewById(R.id.search_text);
        searchBar = (LinearLayout)this.findViewById(R.id.friend_search);
        searchBar.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				// TODO Auto-generated method stub
				searchText.requestFocus();
			}
        	
        });
        searchBar.setOnFocusChangeListener(new OnFocusChangeListener(){

			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				searchText.requestFocus();
			
			}});
        
        FillList(false);
        
    }

    public void openSearchActivity(View target)
    {
    	myNavigator.switchTo(Navigator.FindHimActivitySEQ);
    }
    
    public void showFamily(View target)
    {
    	FillList(false);
    }
    
    public void showFriend(View target)
    {
    	FillList(true);
    }
    
    private void FillList(boolean isFriend)
    {
    	   List<Item> items = new ArrayList<Item>();
    	   for(int i=0;i<10;i++){
    		   items.add(new FriendItem(getFriend(isFriend)));
    	   }
           final ItemAdapter adapter = new ItemAdapter(this, items);
           setListAdapter(adapter);
    }
    
    
    private Friend getFriend(boolean isFriend)
    {
    	User user = new User();
    	user.setId("1");
    	user.setName(isFriend?"宪超":"卡比兽");
    	user.setNumber("186111111");
    	user.setPassword("ttt");
    	
    	Location location = new Location();
    	location.setAddress("北京市海淀区中关村东路1号赛尔大厦22层");
    	location.setLatitude(123);
    	location.setLongitude(123);
    	location.setDateTime(new Date());
    	
    	Friend f = new Friend();
    	f.setUser(user);
    	f.setLocation(location);
    	
    	return f;
    }
    
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_friend, menu);
        return true;
    }*/
}
