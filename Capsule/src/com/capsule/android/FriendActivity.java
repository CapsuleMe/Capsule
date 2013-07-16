package com.capsule.android;

import greendroid.widget.ItemAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.capsule.common.Navigator;



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
    	  /* MyApplication app = (MyApplication)getApplicationContext();
    	   Collection<Friend> coll = app.fManager.getAll(isFriend?Friend.FRIEND:Friend.VIP);
    	   Iterator<Friend> it = coll.iterator();
    	   
    	   List<Item> items = new ArrayList<Item>();
    	   while(it.hasNext()){
    		   items.add(new FriendItem(it.next()));
    	   }
           final ItemAdapter adapter = new ItemAdapter(this, items);
           setListAdapter(adapter);*/
    }
    
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_friend, menu);
        return true;
    }*/
}
