package com.capsule.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.capsule.CapsuleCallbackException;
import com.capsule.CapsuleOpParams;
import com.capsule.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * 常用工具类
 */
public class CommonUtil {

    public static final String SDCARD_PATH=Environment.getExternalStorageDirectory().getAbsolutePath();// 一般相当于"/sdcard"

    private static ProgressDialog mProgressDialog;

    public static AlertDialog.Builder showAlertDialog(Context context, String strTitle, String strMessage, String strPositive,
        DialogInterface.OnClickListener posListener, String strNegative, DialogInterface.OnClickListener negListener,
        View customView) {
        if(context == null) {
            Logger.error("on showAlertDialog context is null ....");
            return null;
        }
        try {
            AlertDialog.Builder anAlert=new AlertDialog.Builder(context);
            anAlert.setTitle(strTitle);
            anAlert.setMessage(strMessage);
            anAlert.setPositiveButton(strPositive, posListener);
            anAlert.setNegativeButton(strNegative, negListener);
            if(customView != null) {
                anAlert.setView(customView);
            }
            anAlert.create();
            anAlert.show();
            return anAlert;
        } catch(Exception e) {
            Logger.error("on showAlertDialog exception happened, msg:" + e.getMessage());
        }
        return null;
    }

    /**
     * 弹出等待对话框
     * @param context
     * @param str
     */
    public static void showLoadingDialog(Context context, String str) {
        try {
            if(context instanceof Activity) {
                if(mProgressDialog == null) {
                    mProgressDialog=new ProgressDialog(context);
                    mProgressDialog.setMessage(str);
                    mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                }
            }
            if(!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch(Exception e) {
            CommonUtil.showWaningToast(context, str);
        }
    }

    /**
     * 关闭等待框
     */
    public static void closeLodingDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }

    /**
     * 弹出提示框
     * @param context
     * @param str
     */
    public static void showWaningToast(Context context, String str) {
        if(context == null)
            return;
        try {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        } catch(Exception e) {
            Logger.error("on showWaningToast exception happened, msg:" + e.getMessage());
        }
    }

    public static void killApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 将字节数组转换为16进制字符串
     * @param _bytes
     * @return
     */
    public static String bytes2HexString(byte[] _bytes) {
        StringBuilder stringBuilder=new StringBuilder("");
        if(_bytes == null || _bytes.length <= 0) {
            Logger.error("on CommonUtil.bytes2HexString _bytes is null !");
            return null;
        }
        for(int i=0; i < _bytes.length; i++) {
            int v=_bytes[i] & 0xFF;
            String hv=Integer.toHexString(v);
            hv=hv.toUpperCase();
            if(hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 调用系统浏览器打开url
     * @param context
     */

    public static boolean startOpenUrl(Context context, String url) {
        try {
            Uri uri=Uri.parse(url);
            Intent intent=new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * change Map to QueryString
     */
    public static String generateQueryString(Map params) {
        if(params == null)
            return "";
        String aQueryParam="";
        if(params.size() > 0) {
            Set aKeySet=params.keySet();
            Iterator aKeyIterator=aKeySet.iterator();
            while(aKeyIterator.hasNext()) {
                String aParamName=(String)aKeyIterator.next();
                String aParamValue=encode((String)params.get(aParamName));
                aQueryParam+=aParamName + "=" + aParamValue + "&";
            }
            aQueryParam=aQueryParam.substring(0, aQueryParam.length() - 1);
        }
        return aQueryParam;
    }

    /**
     * change Map to QueryJson
     */
    public static String generateQueryJson(Map params) {
        if(params == null)
            return "";
        JSONObject aJsonObject=new JSONObject();
        if(params != null && params.size() > 0) {
            try {
                Set aKeySet=params.keySet();
                Iterator aKeyIterator=aKeySet.iterator();
                while(aKeyIterator.hasNext()) {
                    String aParamName=(String)aKeyIterator.next();
                    aJsonObject.put(aParamName, params.get(aParamName));
                }
            } catch(JSONException e) {
            }
            return aJsonObject.toString();
        } else {
            return "";
        }
    }

    /*
     * 解码网络内容
     */
    public static String htmlDecode(String s) {
        if(s == null) {
            return null;
        }
        try {
            s=URLDecoder.decode(s, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 获取SD卡剩余可用空间
     * @return long型字节长度
     */
    public static long getSDCardAvailableCount() {
        StatFs statfs=new StatFs(SDCARD_PATH);
        return ((long)statfs.getAvailableBlocks()) * statfs.getBlockSize();
    }

    /**
     * @param scriptName
     * @param params OpenApi的参数列表
     * @param protocol HTTP请求协议 "http" / "https"
     * @return 返回服务器响应内容
     */
    public static String generateQueryString(String actionName, String method, Map<String, String> params) {

        if(params == null) {
            params=new HashMap<String, String>();
        }
        // 可以在此处增加一些应用的附件参数

        return CommonUtil.generateQueryString(params);
    }

    /**
     * 对value进行编码
     * @param value
     * @return
     */
    public static String encode(String value) {
        if(value == null) {
            return "";
        }
        try {
            return URLEncoder.encode(value, "UTF-8")
            // OAuth encodes some characters differently:
                .replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
            // This could be done faster with more hand-crafted code.
        } catch(UnsupportedEncodingException wow) {
            throw new RuntimeException(wow.getMessage(), wow);

        }

    }

    /**
     * 把以‘&’拼接的参数分解到OpSdkParams里
     * @param paramString
     * @return
     */
    public static CapsuleOpParams getParameters(String paramString) {
        if(TextUtils.isEmpty(paramString)) {
            Logger.debug("on getParamesters paramString is null !!");
            return null;
        }
        if(paramString.startsWith("?")) {
            paramString=paramString.substring(1);
        }

        CapsuleOpParams opParam=new CapsuleOpParams();

        try {
            if(paramString != null && !paramString.equals("")) {
                String[] p=paramString.split("&");
                for(String s: p) {
                    if(s != null && !s.equals("")) {
                        if(s.indexOf('=') > -1) {
                            String[] temp=s.split("=");
                            if(temp.length >= 2)
                                opParam.add(temp[0], temp[1]);
                        }
                    }
                }
            }
        } catch(Exception e) {
            Logger.error("on CommonUtil.getParameters exception happened,msg:" + e.getMessage());
        }
        return opParam;
    }

    /**
     * 查看app是否已经安装
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkAppExist(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo=context.getPackageManager().getPackageInfo(packageName, 0);

        } catch(NameNotFoundException e) {
            packageInfo=null;
        }
        if(packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 解析后台接口返回的错误信息，获取内部错误码和错误字符串
     * @param httpcode http协议应答状态码
     * @param rsp http协议应答内容
     * @return sdk调用异常类，可以获取内部错误码和错误信息，用于与平台定位问题
     */
    public static CapsuleCallbackException parseErrRsp(int httpcode, String rsp) {
        if(rsp == null || rsp.length() == 0) {
            return new CapsuleCallbackException(httpcode, -1, "");
        } else {
            // 组成应答信息格式“错误信息&错误码"
            String[] v=rsp.split("&");
            if(v.length > 1) {
                return new CapsuleCallbackException(httpcode, Integer.valueOf(v[1]).intValue(), v[0]);
            } else if(v.length == 1) {
                return new CapsuleCallbackException(httpcode, -1, v[0]);
            } else
                return new CapsuleCallbackException(httpcode, -1, "");
        }
    }

    /**
     * 返回屏幕的方向 Configuration.ORIENTATION_LANDSCAPE 或者 Configuration.ORIENTATION_PORTRAIT
     * @param context
     * @return
     */
    public static int getSceneOrientation(Context context) {
        return context.getResources().getConfiguration().orientation;
    }
    
    // 检查是否含有scard卡
    public static boolean hasSdcard() {
        String state=Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    // 图片圆角
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas=new Canvas(output);

        final int color=0xff424242;
        final Paint paint=new Paint();
        final Rect rect=new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF=new RectF(rect);
        final float roundPx=pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    //file转换成bitmap
    public static Bitmap fileToBitmap(String filename) {
        try {
            File f=new File(filename);
            if(!f.exists()) {
                return null;
            }
            Bitmap tmp=BitmapFactory.decodeFile(filename);
            return tmp;
        } catch(Exception e) {
            return null;
        }
    }

    // 将图片保存到/data/data/packagename/files下
    public static String bitmapToFile(Context ctx, String imageName, Bitmap mBitmap) throws IOException {
        FileOutputStream fOut=null;
        try {
            String filePath=ctx.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator + imageName;
            fOut=ctx.openFileOutput(imageName, Context.MODE_WORLD_READABLE);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            return filePath;
        } catch(IOException e) {
            e.printStackTrace();
        }finally{
            fOut.flush();
            fOut.close();
        }
        return null;
    }
    
    //创建对话框
    public static AlertDialog dialogCreate(Context ctx, String title, DialogInterface.OnClickListener submitListener, DialogInterface.OnClickListener cancelListener) {
        return new AlertDialog.Builder(ctx).setTitle(title)
            .setPositiveButton(R.string.submit, submitListener)
            .setNegativeButton(R.string.cancel, cancelListener)
            .create();
    }
}
