/**
 * 
 */
package com.capsule;

/**
 * 接口调用异常信息类
 */
public class CapsuleCallbackException extends RuntimeException {

    private static final long serialVersionUID=1L;

    /*
     * HTTP Method Response code Content GET 200 Data acquisition successfully terminated POST 201 Data generation successfully
     * completed POST 202 The request has been accepted for processing, but the processing has not been completed. GET/POST 400
     * Invalid request GET/POST 401 OAuth failure PUT/DELETE 403 Unauthorized method GET 404 Data does not exist GET 500 Tencent
     * Platform error
     */
    public static final int JSON_DATA_ERR=-8000;// json数据格式错误

    public static final int INPUT_DATA_ERR=-9000;// 输入参数错误

    public static final int ERRO_RET_OAUTH_SIGNATURE=-9030;// oauth签名错误

    public static final int ERRO_RET_TOKEN_OVERDUE=-9033;// token过期

    public static final int ERRO_RET_TOKEN_CHANGED=-9035;// Token已经变了

    public static String ERR_MSG_PARAMATER="参数错误";

    private final int errorCode; // http错误码，用于确定接口调用大错误类型

    private final int internalErrorCode; // 平台内部错误码，用于提供定位服务内部错误的具体原因

    private final String errorMessage; // 错误信息

    public CapsuleCallbackException(int errorCode, int internalErr, String errorMessage) {
        super(errorMessage);
        this.errorCode=errorCode;
        this.internalErrorCode=internalErr;
        this.errorMessage=errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getInternalErrorCode() {
        return internalErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // 判断token是否失效，如果返回true，则需要获取新的token
    public boolean bTokenInvalid() {
        if(this.internalErrorCode == this.ERRO_RET_OAUTH_SIGNATURE || this.internalErrorCode == this.ERRO_RET_TOKEN_CHANGED
            || this.internalErrorCode == this.ERRO_RET_TOKEN_OVERDUE) {
            return true;
        } else {
            return false;
        }
    }
}
