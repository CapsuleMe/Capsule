package com.capsule.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class FilterUtil {

    private static PathMatcher pathMatcher=new AntPathMatcher();

    /**
     * 判断所请求的URL 是否匹配所指定url pattern 列表。
     * @param requestUrl 请求的URL
     * @param urlResources URL Pattern 列表
     * @return
     */
    public static boolean checkRequestUrl(final String requestUrl, final List<String> urlResources) {
        boolean isExisted=false;

        if(urlResources != null) {
            for(String url: urlResources) {
                isExisted=pathMatcher.match(url.toLowerCase(), requestUrl.toLowerCase());
                if(isExisted) {
                    break;
                }
            }
        }
        return isExisted;
    }

    /**
     * @param urlPatterns
     * @return
     */
    public static List<String> spliteUrlPatterns(String urlPatterns) {
        List<String> urls=null;
        if(StringUtils.isNotBlank(urlPatterns)) {
            String[] tmp=StringUtils.split(urlPatterns, "\n");
            urls=new ArrayList<String>();

            for(String url: tmp) {
                if(StringUtils.isNotBlank(url)) {
                    urls.add(url.trim());
                }
            }
        }

        return urls;
    }

    /**
     * 获得请求路径的URI
     * @param req ServletRequest
     * @return
     */
    public static String getRequestedUrl(ServletRequest req){
        final HttpServletRequest request = (HttpServletRequest) req;
        // 获得URL
        String url = request.getRequestURI();
        String context = request.getContextPath();
        if(url.startsWith(context+"/")) {
            url = url.replaceFirst(context, "");
        }
        
        return url;
    }
}
