package com.capsule.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.capsule.CapsuleOpParams;

/**
 * 上传图片协议的处理
 */
public class PostImageUtility {

    public static final String BOUNDARY="c9152e99a2d6487fb0bfd02adec3aa16";

    public static final String MP_BOUNDARY="--" + BOUNDARY;

    public static final String END_MP_BOUNDARY="--" + BOUNDARY + "--";

    public static final String MULTIPART_FORM_DATA="multipart/form-data";

    public static final long MAX_FILE_SIZE=4 * 1024 * 1024; // 4M

   
    private static void imageContentToUpload(OutputStream out, String key, File file) throws Exception {

        StringBuilder temp=new StringBuilder();
        temp.append(MP_BOUNDARY).append("\r\n");
        temp.append("Content-Disposition: form-data; name=\"");
        temp.append(key);
        temp.append("\"; filename=\"").append(file.getName()).append("\"\r\n");
        String filetype="image/png";
        temp.append("Content-Type: ").append(filetype).append("\r\n\r\n");
        byte[] res=temp.toString().getBytes();
        BufferedInputStream bis=null;
        try {

            out.write(res);

            FileInputStream in=new FileInputStream(file);
            byte[] buffer=new byte[in.available()];
            Logger.error("imageContentToUpload fileName" + file.getName() + ",length = " + buffer.length);
            in.read(buffer);
            out.write(buffer);

            out.write("\r\n".getBytes());
            in.close();
        } catch(IOException e) {
            throw new Exception(e);
        } finally {
            if(null != bis) {
                try {
                    bis.close();
                } catch(IOException e) {
                    throw new Exception(e);
                }
            }
        }
    }

    /**
     * 上传图片
     * @param out
     * @param files
     * @throws Exception
     */
    public static void imageContentToUpload(OutputStream out, Map<String, File> files) throws Exception {

        Iterator<Entry<String, File>> iterator=files.entrySet().iterator();
        while(iterator.hasNext()) {
            Entry<String, File> entry=(Entry<String, File>)iterator.next();
            String key=entry.getKey();
            File file=entry.getValue();
            imageContentToUpload(out, key, file);

        }
        out.write(("\r\n" + END_MP_BOUNDARY).getBytes());

    }

    /**
     * 上传图片
     * @param baos
     * @param params
     * @throws Exception
     */
    public static void paramToUpload(OutputStream baos, CapsuleOpParams params) throws Exception {
        String key="";
        for(int loc=0; loc < params.size(); loc++) {
            key=params.getKey(loc);
            StringBuilder temp=new StringBuilder(10);
            temp.setLength(0);
            temp.append(MP_BOUNDARY).append("\r\n");
            temp.append("content-disposition: form-data; name=\"").append(key).append("\"\r\n\r\n");
            temp.append(params.getValue(key)).append("\r\n");
            byte[] res=temp.toString().getBytes();
            try {
                baos.write(res);
            } catch(IOException e) {
                throw new Exception(e);
            }
        }
    }

}
