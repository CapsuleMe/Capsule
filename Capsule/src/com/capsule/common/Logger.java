package com.capsule.common;


import com.capsule.AppConfig;

import android.util.Log;

/**
 * 用于打统一日志,开放平台Android SDK调试日志以 TAG="CapsuleLog" 的形式打出  里通过开关isDebug开启日志和关闭日志，应用发布前一定要关闭日志，提供应用运行性能
 */
public class Logger {

    public static final String CA_LOG="CapsuleLog";

    /**
     * 调试信息
     * @param tag
     * @param msg
     */
    public static void error(String TAG, String msg) {
        if(!AppConfig.isLogOpen())
            return;
        Log.e(TAG, msg);
    }

    /**
     * 调试信息
     * @param msg
     */
    public static void error(String msg) {
        if(!AppConfig.isLogOpen())
            return;
        Log.e(CA_LOG, msg);
    }

    /**
     * 调试信息
     * @param tag
     * @param msg
     */
    public static void debug(String TAG, String msg) {
        if(!AppConfig.isLogOpen())
            return;
        Log.d(TAG, msg);
    }

    /**
     * 调试信息
     * @param tag
     * @param msg
     */
    public static void debug(String msg) {
        if(!AppConfig.isLogOpen())
            return;
        Log.d(CA_LOG, msg);
    }

    /**
     * 调试信息,系统级别,打印出必要的数据,不会关闭
     * @param tag
     * @param msg
     */
    public static void sysLog(String msg) {
        Log.d(CA_LOG, msg);
    }
}
