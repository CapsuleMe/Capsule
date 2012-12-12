package com.capsule.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SearchFriendActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_friend, menu);
        return true;
    }
    
    public void onSearch(View target)
    {
    	
    }
}
