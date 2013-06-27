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

import com.capsule.android.widget.item.MessageItem;
import com.capsule.model.Message;
import com.capsule.model.User;

public class MessageActivity extends ListActivity {

	ItemAdapter adapter  = null;
	LinearLayout searchBar = null;
	EditText searchText = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        
        searchText = (EditText)this.findViewById(R.id.message_search_text);
        searchBar = (LinearLayout)this.findViewById(R.id.message_search);
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
        
        FillList();
        
    }

    private void FillList()
    {
    	   List<Item> items = new ArrayList<Item>();
           
           items.add(new MessageItem(getMessage()));   
           
           final ItemAdapter adapter = new ItemAdapter(this, items);
           setListAdapter(adapter);
    }
    
    
    private Message getMessage()
    {
    	User user = new User();
    	user.setId("1");
    	user.setName("ø®±» ﬁ");
    	user.setNumber("186111111");
    	user.setPassword("ttt");
    	
    	
    	Message msg = new Message();
    	msg.setUser(user);
    	msg.setContent("ÀµŒ“∞Æƒ„");
    	msg.setDatetime(new Date());
    	
    	return msg;
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_message, menu);
        return true;
    }*/
}