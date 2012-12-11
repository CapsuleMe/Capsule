package com.capsule.android;

import com.capsule.common.SharePreferencesEditor;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class SettingActivity extends BaseActivity{

    private SharePreferencesEditor preferences;
    
    private Drawable closeRadio;
    
    private Drawable openRadio;
    
    private ImageView isWarningBtn;
    
    private ImageView isHideBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initSetting();
    }
    
    private void initSetting(){
        preferences=new SharePreferencesEditor(this, SharePreferencesEditor.SettingsName);
        openRadio=this.getResources().getDrawable(R.drawable.radio_box_open);
        closeRadio=this.getResources().getDrawable(R.drawable.radio_box_close);
        isWarningBtn=(ImageView)findViewById(R.id.isWarning_btn);
        isHideBtn=(ImageView)findViewById(R.id.isHide_btn);
        boolean isHide=Boolean.valueOf(preferences.get("isHide", "true"));// 默认开启=true
        boolean isWarning=Boolean.valueOf(preferences.get("isWarning", "true"));// 默认开启=true
        setRadioImage(isHide, isHideBtn);
        setRadioImage(isWarning, isWarningBtn);
    }
    
    public void updatePositionRate(View target){
        
    }
    
    public void warningSetting(View target){
        boolean isWarning=Boolean.valueOf(preferences.get("isWarning", "true"));// 默认开启=true
        isWarningBtn=(ImageView)target;
        setRadioImage(!isWarning, isWarningBtn);
        preferences.put("isWarning", String.valueOf(!isWarning));
    }
    
    public void hideSetting(View target){
        boolean isHide=Boolean.valueOf(preferences.get("isHide", "true"));// 默认开启=true
        isHideBtn=(ImageView)target;
        setRadioImage(!isHide, isHideBtn);
        preferences.put("isHide", String.valueOf(!isHide));
        
    }
    
    private void setRadioImage(boolean isOpen, ImageView radioView){
        if(isOpen){
            radioView.setImageDrawable(openRadio);
        }else{
            radioView.setImageDrawable(closeRadio);
        }
    }
}
