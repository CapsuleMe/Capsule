package com.capsule.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SearchFriendActivity extends Activity {

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
}
