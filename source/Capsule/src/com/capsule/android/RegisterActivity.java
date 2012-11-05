package com.capsule.android;

import android.os.Bundle;
import android.widget.TextView;


public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textAgreement=(TextView)findViewById(R.id.textAgreement2);
        textAgreement.getPaint().setFakeBoldText(true);
    }
    
}
