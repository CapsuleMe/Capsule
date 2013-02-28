package com.capsule.common.network;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;

import com.capsule.CapsuleOpParams;
import com.capsule.common.CommonUtil;
import com.capsule.common.HttpUtil;
import com.capsule.common.Logger;
import com.capsule.common.PostImageUtility;

/**
 * http同步通讯
 * 同步请求使用请注意，在android4.0中请求网络不能在主线程中，会报错android.os.NetworkOnMainThreadException
 * 尽量使用异步请求网络
 */
public class SyncHttpConnection {

    private static final int GET_METHOD_INDEX=0;

    private static final int POST_METHOD_INDEX=1;

    private String url;

    private Map<String, File> postFile;

    private Map<String, String> params;

    private String httpMethod;

    private String action;

    public SyncHttpConnection(String url, String action, String httpMethod, Map<String, String> params,
        Map<String, File> postFile) {
        this.url=url;
        this.action=action;
        this.httpMethod=httpMethod;
        this.params=params;
        this.postFile=postFile;
    }

    public String execute() {
        String aQueryParam="";

        String uri="";

        int method=GET_METHOD_INDEX;
        if(httpMethod.toLowerCase().equals(AsyncHttpConnection.GET_METHOD)) {
            aQueryParam=CommonUtil.generateQueryString(action, httpMethod.toLowerCase(), params);
            uri=url + action + "?" + aQueryParam;

            method=GET_METHOD_INDEX;

        } else if(httpMethod.toLowerCase().equals(AsyncHttpConnection.POST_METHOD)) {
            aQueryParam=CommonUtil.generateQueryString(httpMethod, httpMethod.toLowerCase(), params);

            uri=url + action;

            method=POST_METHOD_INDEX;
        }

        HttpClient httpClient=new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), HttpUtil.getTimeoutForHTTPConnection());
        try {
            HttpResponse response=null;
            Logger.debug("HttpUrl=" + uri);

            switch(method) {
                case GET_METHOD_INDEX: {
                    HttpGet httpGet=new HttpGet(uri);
                    response=httpClient.execute(httpGet);
                    break;
                }
                case POST_METHOD_INDEX: {
                    HttpPost httpPost=new HttpPost(uri);

                    if(postFile == null || postFile.size() <= 0) { // 没有post文件
                        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                        httpPost.setEntity(new StringEntity(aQueryParam, "utf-8"));

                    } else {
                        httpPost.setHeader("Content-Type", "multipart/form-data" + "; boundary=" + PostImageUtility.BOUNDARY);
                        ByteArrayOutputStream bos=null;
                        bos=new ByteArrayOutputStream(100 * 1024);
                        CapsuleOpParams opParam=null;
                        opParam=CommonUtil.getParameters(aQueryParam);
                        PostImageUtility.paramToUpload(bos, opParam);
                        PostImageUtility.imageContentToUpload(bos, postFile);
                        byte temp[]=bos.toByteArray();
                        ByteArrayEntity formEntity=new ByteArrayEntity(temp);
                        httpPost.setEntity(formEntity);
                    }
                    // 关闭Expect:100-Continue握手
                    httpPost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
                    response=httpClient.execute(httpPost);

                    break;
                }
            }

            if(response.getStatusLine().getStatusCode() != AsyncHttpConnection.GET_SUCCEED_STATUS
                && response.getStatusLine().getStatusCode() != AsyncHttpConnection.POST_SUCCEED_STATUS) { // 认证出错
                return null;
            } else {
                return processEntity(response.getEntity());
            }
        } catch(Exception e) {
            Logger.debug("SyncHttpConnection()", e.toString());
        }
        return null;
    }

    /**
     * 读取返回结果
     * @param entity
     * @param statusCode
     * @throws IllegalStateException
     * @throws IOException
     */
    private String processEntity(HttpEntity entity) throws IllegalStateException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(entity.getContent()));
        String line, result="";
        StringBuilder sBuilder=new StringBuilder(result);
        while((line=br.readLine()) != null) {
            sBuilder.append(line);
        }
        result=sBuilder.toString();
        br.close();
        return result;
    }
}
