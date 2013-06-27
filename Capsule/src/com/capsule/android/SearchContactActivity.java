package com.capsule.android;

import greendroid.widget.ItemAdapter;
import greendroid.widget.item.Item;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

import com.capsule.android.widget.item.SearchContactItem;
import com.capsule.model.TempUser;

public class SearchContactActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        
        Fill();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_friend_result, menu);
        return true;
    }

    public void Fill()
    {
    	List<Item> items = new ArrayList<Item>();
    	
    	TempUser u1 = new TempUser();
    	u1.setName("ºÃÓÑ");
    	u1.setFriend(true);
    	
    	TempUser u2 = new TempUser();
    	u2.setName("×¢²áÓÃ»§");
    	u2.setUser(true);
    	
    	TempUser u3 = new TempUser();
    	u3.setName("Î´×¢²á");

    	items.add(new SearchContactItem(u1));
    	items.add(new SearchContactItem(u2));
    	items.add(new SearchContactItem(u3));
    	
    	 final ItemAdapter adapter = new ItemAdapter(this, items);
         setListAdapter(adapter);
    }

}