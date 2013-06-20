package com.capsule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数封装类
 * @create-time 2012-3-13 下午12:32:17
 */

public class CapsuleOpParams {

    private Map<String, String> mParameters=new HashMap<String, String>();

    private List<String> mKeys=new ArrayList<String>();

    public CapsuleOpParams() {

    }

    public void add(String key, String value) {
        if(this.mKeys.contains(key)) {
            this.mParameters.put(key, value);
        } else {
            this.mKeys.add(key);
            this.mParameters.put(key, value);
        }
    }

    public void remove(String key) {
        mKeys.remove(key);
        this.mParameters.remove(key);
    }

    public String getValue(String key) {
        String rlt=this.mParameters.get(key);
        return rlt;
    }

    public String getValue(int location) {
        String key=this.mKeys.get(location);
        String rlt=this.mParameters.get(key);
        return rlt;
    }

    public String getKey(int location) {
        if(location >= 0 && location < this.mKeys.size()) {
            return this.mKeys.get(location);
        }
        return "";
    }

    public int size() {
        return mKeys.size();
    }

    public void clear() {
        this.mKeys.clear();
        this.mParameters.clear();
    }

}
