package com.capsule.android;

import com.capsule.common.Navigator;
import com.capsule.common.SharePreferencesEditor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


public class SettingActivity extends BaseActivity{

    private SharePreferencesEditor preferences;
    
    private Drawable closeRadio;
    
    private Drawable openRadio;
    
    private ImageView isWarningBtn;
    
    private ImageView isHideBtn;
    
    private PopupWindow popup;
    
    private String[][] rateArray=new String[][] { {"120", "2小时"}, {"60", "1小时"}, {"30", "30分钟"}, 
        {"15", "15分钟"}, {"10", "10分钟"}, {"5","5分钟"}, {"1", "1分钟"} };
    
    private int rateIndex=rateArray.length-1;

    private TextView rateText;

    private TextView menuTxt;

    private Button logoutBtn;

    private Button cancelBtn;

    private View menuView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initSetting();
    }
    
    private void initSetting(){
        View topbar=findViewById(R.id.top_bar);
        TextView titlebar=(TextView)topbar.findViewById(R.id.top_bar_title);
        titlebar.setText(this.getString(R.string.setting));
       
        preferences=new SharePreferencesEditor(this, SharePreferencesEditor.SettingsName);
        openRadio=this.getResources().getDrawable(R.drawable.radio_box_open);
        closeRadio=this.getResources().getDrawable(R.drawable.radio_box_close);
        isWarningBtn=(ImageView)findViewById(R.id.isWarning_btn);
        isHideBtn=(ImageView)findViewById(R.id.isHide_btn);
        boolean isHide=Boolean.valueOf(preferences.get("isHide", "true"));// 默认开启=true
        boolean isWarning=Boolean.valueOf(preferences.get("isWarning", "true"));// 默认开启=true
        setRadioImage(isHide, isHideBtn);
        setRadioImage(isWarning, isWarningBtn);

        rateIndex=preferences.get("rate", rateIndex);
        rateText=(TextView)findViewById(R.id.rateText);
        rateText.setText(rateArray[rateIndex][1]);
        
        initPopuWindows();
    }
    
    public void updatePositionRate(View target){
        rateIndex=preferences.get("rate", rateIndex);
        String[] items=new String[rateArray.length];
        for(int i=0; i<items.length; i++){
            items[i]=rateArray[i][1];
        }
        new AlertDialog.Builder(this).setSingleChoiceItems(
            items, rateIndex, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String minute=rateArray[which][0];
                    preferences.put("rate", which);
                    preferences.put("updateMinute", minute);
                    rateText.setText(rateArray[which][1]);
                    dialog.dismiss();
                }
         }).show();
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

    public void showAgreement(View target){
        myNavigator.switchTo(Navigator.AgreementActivitySEQ);
    }

    public void findWays(View target){
        myNavigator.switchTo(Navigator.FindHimActivitySEQ);
    }
    
    private void initPopuWindows(){
        menuView=View.inflate(this, R.layout.menu_confirm_layout, null);
        menuView.setFocusableInTouchMode(true);
        cancelBtn=(Button)menuView.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popup.dismiss();  
            }
        });
        menuTxt=(TextView)menuView.findViewById(R.id.menu_text);
        logoutBtn=(Button)menuView.findViewById(R.id.logoutBtn);
        popup = new PopupWindow(menuView, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        popup.setBackgroundDrawable(getResources().getDrawable(R.drawable.menu_background));
        popup.setFocusable(true);
        popup.setAnimationStyle(R.style.menushow);
        popup.update();
        menuView.setOnKeyListener(new android.view.View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if ((keyCode == KeyEvent.KEYCODE_MENU) && (popup.isShowing())) {
                    popup.dismiss();
                    return true;
                }
                return false;
            }
        });
    }
    
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return false;// 返回为true 则显示系统menu
    }
    
    public void exitCapsule(View target){
        menuTxt.setText(R.string.exit_tip_content);
        popup.showAtLocation(this.findViewById(R.id.setting_layout), Gravity.BOTTOM, 0, 0);
        logoutBtn.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                popup.dismiss();
                System.exit(0);
            }
        });
    }
    
    public void logoutCapsult(View target){
        menuTxt.setText(R.string.logout_tip_content);
        popup.showAtLocation(this.findViewById(R.id.setting_layout), Gravity.BOTTOM, 0, 0);
        logoutBtn.setText(R.string.logout);
        logoutBtn.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //清空用户本地密码等操作
                popup.dismiss();
                myNavigator.switchTo(Navigator.MainActivitySEQ);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
