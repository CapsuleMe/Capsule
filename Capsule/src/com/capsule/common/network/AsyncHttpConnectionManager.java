package com.capsule.common.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http异步通讯管理
 */
public class AsyncHttpConnectionManager {

    public static final int MAX_CONNECTIONS=5;

    private static AsyncHttpConnectionManager instance;

    private ExecutorService mThreadPool=Executors.newFixedThreadPool(MAX_CONNECTIONS);

    public static AsyncHttpConnectionManager getInstance() {
        if(instance == null) {
            instance=new AsyncHttpConnectionManager();
        }
        return instance;
    }

    public void submit(Runnable runnable) {
        mThreadPool.submit(runnable);

    }

}
