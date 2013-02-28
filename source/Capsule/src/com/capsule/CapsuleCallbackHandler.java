/**
 * 
 */
package com.capsule;

/**
 * sdk调用回调处理接口
 * @author teajtang
 */
public interface CapsuleCallbackHandler {

    /**
     * REST api接口调用成功后的http应答内容和http statu code
     * @param rspContent 应答的http正文
     * @param httpCode 应答的http状态码
     */

    void onSuccess(String rspContent, int statusCode);

    /**
     * REST api接口调用失败后的http应答内容和http statu code
     * @param exp 异常信息类，里面包括错误原因和内部错误码，用于定位问题。
     */
    void onFailure(CapsuleCallbackException exp);

}
