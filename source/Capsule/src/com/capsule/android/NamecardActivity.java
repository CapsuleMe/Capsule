package com.capsule.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class NamecardActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namecard);
    }

    public void openCardSettingActivity(View target)
    {
    	myNavigator.switchTo(CardSettingActivity.class);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_namecard, menu);
        return true;
    }
}
