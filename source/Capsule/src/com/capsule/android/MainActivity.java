package com.capsule.android;

import com.capsule.common.Navigator;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        View layout=findViewById(R.id.welcomeLayout);
        layout.getBackground().setAlpha(180);
        
       myNavigator.switchTo(Navigator.BottomTabActivitySEQ);
    }

    
    public void goRegister(View v){
        myNavigator.switchTo(Navigator.RegistActivitySEQ);
    }
    
    public void goLogin(View v){
        myNavigator.switchTo(Navigator.LoginActivitySEQ);
    }
}
