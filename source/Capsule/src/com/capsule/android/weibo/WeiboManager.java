package com.capsule.android.weibo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;


public class WeiboManager{

    private static Weibo mWeibo;
    
    private static final String CONSUMER_KEY = "966056985";// 替换为开发者的appkey，例如"1646212860";
    
    private static final String REDIRECT_URL = "http://www.sina.com";
    
    public static Oauth2AccessToken accessToken;
    
    private static WeiboManager weiBoInstance;
    
    private static Context context;
    
    private WeiboManager(){}
    
    public static WeiboManager getWeiboInstance(Context context){
        if(weiBoInstance == null){
            weiBoInstance=new WeiboManager();
            mWeibo = Weibo.getInstance(CONSUMER_KEY, REDIRECT_URL);
        }
        WeiboManager.context=context;
        return weiBoInstance;
    }
    
    class AuthDialogListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle arg0) {
            
        }

        @Override
        public void onError(WeiboDialogError e) {
            Toast.makeText(WeiboManager.context, "Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(WeiboManager.context, "Auth cancel", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(WeiboManager.context, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
