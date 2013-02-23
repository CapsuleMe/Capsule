package com.capsule;

/**
 * 应用常用配置信息
 *
 */
public class AppConfig {

    //日志开关 应用在调试程序期间可以将B_LOG_OPEN设置为true开启日志功能，方便调试，在发布程序前一定把B_LOG_OPEN设置为false，提高程序性能
    private static boolean B_LOG_OPEN = true;
    
    public static boolean isLogOpen(){
        return B_LOG_OPEN;
    }
}
