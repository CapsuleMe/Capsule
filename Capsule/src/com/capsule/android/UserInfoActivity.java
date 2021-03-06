package com.capsule.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class UserInfoActivity extends Activity {

    private Button updateGPSBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        initUI();
    }

    private void initUI() {
        updateGPSBtn=(Button)findViewById(R.id.updateGPSBtn);
        updateGPSBtn.setOnClickListener(new UpdateGPSListener());
    }

    
    private class UpdateGPSListener implements OnClickListener{

        public void onClick(View v) {
            ImageView loadImg=(ImageView)findViewById(R.id.loading_image);
            loadImg.setVisibility(0);//0：可见；4不可见； 8 不可见且不占用空间
            updateGPSBtn.setVisibility(4);
        }
        
    }
}
