package com.capsule.android;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class FindWaysActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ways);
        View topbar=findViewById(R.id.top_bar);
        TextView titlebar=(TextView)topbar.findViewById(R.id.top_bar_title);
        titlebar.setText(this.getString(R.string.find_him));
       
    }
}
