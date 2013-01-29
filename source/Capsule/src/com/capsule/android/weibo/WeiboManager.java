package com.capsule.android.weibo;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;

public class WeiboManager {

    private static Weibo mWeibo;
    
    public final static int AUTH_SUCCESS=1;
    
    public final static int GET_USER_INFO=2;

    private static final String CONSUMER_KEY="966056985";// 替换为开发者的appkey，例如"1646212860";

    private static final String REDIRECT_URL="http://www.sina.com";

    public static Oauth2AccessToken accessToken;

    private static WeiboManager weiBoInstance;

    private static Context context;

    private WeiboManager() {
    }

    public static WeiboManager getWeiboInstance() {
        if(weiBoInstance == null) {
            weiBoInstance=new WeiboManager();
            mWeibo=Weibo.getInstance(CONSUMER_KEY, REDIRECT_URL);
        }
        return weiBoInstance;
    }

    public WeiboManager authorize(Context context, final Handler handler) {
        WeiboManager.context=context;
        mWeibo.authorize(context, new AuthDialogListener(handler));
        return this;
    }

    public static void getUserInfo(Context context, final Handler handler) {
        final Bundle data=new Bundle();
        AccountAPI accountApi=new AccountAPI(WeiboManager.accessToken);
        accountApi.getUid(new AbstractRequestListener() {

            @Override
            public void onComplete(String res) {
                try {
                    JSONObject json=new JSONObject(res);
                    String uid=json.getString("uid");
                    UsersAPI userApi=new UsersAPI(WeiboManager.accessToken);
                    userApi.show(Long.valueOf(uid), new AbstractRequestListener(){

                        @Override
                        public void onComplete(String res) {
                            try {
                                JSONObject json=new JSONObject(res);
                                String screen_name=json.getString("screen_name");
                                data.putString("screen_name", screen_name);
                                Message msg=new Message();
                                msg.setData(data);
                                msg.what=WeiboManager.GET_USER_INFO;
                                handler.sendMessage(msg);
                            } catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }
    /**
     * 微博退出
     * @param listener
     */
    public void logOutWeibo(RequestListener listener){
        AccountAPI accountApi=new AccountAPI(WeiboManager.accessToken);
        accountApi.endSession(listener);
    }
 
    class AuthDialogListener implements WeiboAuthListener {

        private Handler handler;
        
        public AuthDialogListener(Handler handler){
            this.handler=handler;
        }

        public void onComplete(Bundle values) {
            try {
                String token=values.getString("access_token");
                String expires_in=values.getString("expires_in");
                WeiboManager.accessToken=new Oauth2AccessToken(token, expires_in);
                if(WeiboManager.accessToken.isSessionValid()) {
                    Toast.makeText(WeiboManager.context, "新浪微博认证成功", Toast.LENGTH_SHORT).show();
                    Message msg=new Message();
                    msg.what=WeiboManager.AUTH_SUCCESS;
                    handler.sendMessage(msg);
                }
            } catch(Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void onError(WeiboDialogError e) {
            Toast.makeText(WeiboManager.context, "Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        public void onCancel() {
            Toast.makeText(WeiboManager.context, "Auth cancel", Toast.LENGTH_LONG).show();
        }

        public void onWeiboException(WeiboException e) {
            Toast.makeText(WeiboManager.context, "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    
}
