package com.capsule.android.weibo;

import java.io.IOException;

import com.weibo.sdk.android.WeiboException;
import com.weibo.sdk.android.net.RequestListener;


public abstract class AbstractRequestListener implements RequestListener {

    public abstract void onComplete(String arg0);


    public void onError(WeiboException arg0) {
        // TODO Auto-generated method stub

    }


    public void onIOException(IOException arg0) {
        // TODO Auto-generated method stub

    }

}
