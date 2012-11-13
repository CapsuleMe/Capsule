package com.capsule.android;

import greendroid.widget.ItemAdapter;
import greendroid.widget.item.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;

import com.capsule.android.widget.item.FriendItem;
import com.capsule.model.Friend;
import com.capsule.model.Location;
import com.capsule.model.User;



public class FriendActivity extends ListActivity {

	ItemAdapter adapter  = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        
        List<Item> items = new ArrayList<Item>();
        
        items.add(new FriendItem(getFriend()));   
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));     
        items.add(new FriendItem(getFriend()));   
        
        final ItemAdapter adapter = new ItemAdapter(this, items);
        setListAdapter(adapter);
        
    }

    private Friend getFriend()
    {
    	User user = new User();
    	user.setId("1");
    	user.setName("卡比兽");
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
