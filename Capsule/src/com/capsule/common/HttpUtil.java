package com.capsule.common;

import java.io.File;
import java.util.Map;

import com.capsule.CapsuleCallbackException;
import com.capsule.CapsuleCallbackHandler;
import com.capsule.common.network.AsyncHttpConnection;
import com.capsule.common.network.SyncHttpConnection;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class HttpUtil {

    private static HttpUtil mInstance = null;
    
    private static Context mContext=null;
    
    public static final String GET_METHOD = "get";
    public static final String POST_METHOD = "post";//post非json数据
    public static final String POST_JSON_METHOD = "post_json";//post json数据

    public static HttpUtil getInstance(){
        if(mInstance == null){
            mInstance = new HttpUtil();
        }
        return mInstance;
    }
    
    public static Context getContext() {
        if(mContext == null) {
            throw new RuntimeException("请先通过HttpUtil.setContext(context)设置上下文！");
        }
        return mContext;
    }

    /**
     * 设置上下文，使用sdk接口前必须先设置上下文
     * @param mContext
     */
    public static void setContext(Context mContext) {
        if(HttpUtil.mContext != null) {
            HttpUtil.mContext=null;
        }
        HttpUtil.mContext=mContext;
    }

    public static int getTimeoutForHTTPConnection() {
        return 30000;
    }

    class InternalHandler extends Handler {

        private Handler mParentHandler=null;

        private CapsuleCallbackHandler callBackHandler=null;

        public InternalHandler(Handler parentHandler) {
            this.mParentHandler=parentHandler;
        }

        public Handler getParentHandler() {
            return mParentHandler;
        }

        public CapsuleCallbackHandler getCallBackHandler() {
            return callBackHandler;
        }

        public void setCallBackHandler(CapsuleCallbackHandler callBackHandler) {
            this.callBackHandler=callBackHandler;
        }
    }

    /**
     * 异步http请求
     * @param url 请求域名或url前缀
     * @param actionName 调用地址
     * @param method post/get
     * @param params 传递的参数
     * @param handler 内部handler，用户发送请求调用结果
     * @return
     */
    private boolean requestActionAsync(String url, String action, String method, Map<String, String> params,
        Map<String, File> files, Handler handler) {
        String aQueryParam;
        if(method.toLowerCase().equals(AsyncHttpConnection.GET_METHOD)) {
            aQueryParam=CommonUtil.generateQueryString(action, method.toLowerCase(), params);
            new AsyncHttpConnection(handler).get(url + action + "?" + aQueryParam);
        } else if(method.toLowerCase().equals(AsyncHttpConnection.POST_METHOD)) {
            aQueryParam=CommonUtil.generateQueryString(action, method.toLowerCase(), params);
            new AsyncHttpConnection(handler).post(url + action, aQueryParam, files);
        } else if(method.toLowerCase().equals(AsyncHttpConnection.POST_JSON_METHOD)) {
            aQueryParam=CommonUtil.generateQueryJson(params);
            new AsyncHttpConnection(handler).postJson(url + action, aQueryParam);
        }
        return true;
    }

    /**
     * 发送协议
     * @param url 请求的接口url地址
     * @param httpMethod http方法，post或get
     * @param params http请求参数
     * @param h callback
     * @return
     */
    public boolean httpAsynSend(String url, String action, String httpMethod, Map<String, String> params,
        CapsuleCallbackHandler h) {
        return httpAsynSend(url, action, httpMethod, params, null, h);
    }

    /**
     * 带文件的发送协议
     * @param url 请求的接口url地址
     * @param httpMethod http方法，post或get
     * @param params http请求参数
     * @param files 上传的文件参数,Map中key为协议key,value为文件的本地路径
     * @param h callback
     * @return
     */
    public boolean httpAsynSend(String url, String action, String httpMethod, Map<String, String> params,
        Map<String, File> files, CapsuleCallbackHandler h) {

        InternalHandler anInternalHandler=new InternalHandler(null) {

            public void handleMessage(Message message) {
                switch(message.what) {
                    case AsyncHttpConnection.DID_START:
                        break;
                    case AsyncHttpConnection.DID_SUCCEED:
                        String[] ret=(String[])message.obj;
                        Integer httpCode=Integer.valueOf(ret[0]);
                        if(this.getCallBackHandler() == null) {
                            break;
                        }
                        if(httpCode.intValue() == AsyncHttpConnection.GET_SUCCEED_STATUS
                            || httpCode.intValue() == AsyncHttpConnection.POST_SUCCEED_STATUS) {
                            this.getCallBackHandler().onSuccess(ret[1], httpCode.intValue());
                        } else {
                            this.getCallBackHandler().onFailure(CommonUtil.parseErrRsp(httpCode.intValue(), ret[1]));
                        }
                        break;

                    case AsyncHttpConnection.DID_ERROR: {
                        CommonUtil.showWaningToast(mContext, "上次失败");
                        if(this.getCallBackHandler() == null) {
                            break;
                        }
                        this.getCallBackHandler().onFailure(new CapsuleCallbackException(-1, -1, AsyncHttpConnection.ERR_MSG_REQ));
                        break;
                    }
                    case AsyncHttpConnection.DID_OAUTH_ERROR: {
                        try {
                            String[] oauthErroRet=(String[])message.obj;
                            Integer httpErroCode=Integer.valueOf(oauthErroRet[0]);
                            if(httpErroCode.intValue() == AsyncHttpConnection.OAUTH_ERROR_STATUS) {
                                CommonUtil.showWaningToast(mContext, "认证失败");
                                return;
                            }

                            if(oauthErroRet != null && oauthErroRet.length >= 2) {
                                if(this.getCallBackHandler() == null) {
                                    break;
                                }
                                this.getCallBackHandler().onFailure(
                                    CommonUtil.parseErrRsp(httpErroCode.intValue(), oauthErroRet[1]));
                            } else {
                                if(this.getCallBackHandler() == null) {
                                    break;
                                }
                                this.getCallBackHandler().onFailure(
                                    new CapsuleCallbackException(401, -1, AsyncHttpConnection.ERR_MSG_REQ));
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                            if(this.getCallBackHandler() == null) {
                                break;
                            }
                            this.getCallBackHandler().onFailure(
                                new CapsuleCallbackException(401, -1, AsyncHttpConnection.ERR_MSG_REQ));
                        }
                        break;
                    }
                }
            }
        };

        anInternalHandler.setCallBackHandler(h);
        return requestActionAsync(url, action, httpMethod, params, files, anInternalHandler);
    }

    /**
     * 发送协议同步
     * @param url 请求的接口url地址
     * @param httpMethod http方法，post或get
     * @param params http请求参数
     * @param h callback
     * @return
     */
    public String httpSynSend(String url, String action, String httpMethod, Map<String, String> params,
        Map<String, File> postFile) {
        return new SyncHttpConnection(url, action, httpMethod, params, postFile).execute();
    }
    
    public String httpSynSend(String url, String action, String httpMethod, Map<String, String> params) {
        return httpSynSend(url, action, httpMethod, params, null);
    }
}
