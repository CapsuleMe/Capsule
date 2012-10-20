package com.capsule.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.capsule.common.util.FilterUtil;

public class RewriteFilter implements Filter {

    final static Logger loger=Logger.getLogger(RewriteFilter.class);

    private String excludedUrlPatterns="/remoting/*";

    private List<String> excludedUrls;

    private String REWRITE_SUFFIX=".action";

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // pass the request along the filter chain
        final HttpServletRequest req=(HttpServletRequest)request;

        final String url=FilterUtil.getRequestedUrl(req);

        loger.debug("url =============================" + url);

        if(url.length() > 1 && !url.contains(".")) {
            final boolean isExcludedUrl=FilterUtil.checkRequestUrl(url, excludedUrls);
            if(!isExcludedUrl) {
                String rewriteUri=url;
                if(rewriteUri.length() > 1) {
                    rewriteUri+=REWRITE_SUFFIX;
                }

                req.getRequestDispatcher(rewriteUri).forward(req, response);

                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

        // 获得初始化参数
        String param=config.getInitParameter("rewriteFuffix");
        if(StringUtils.isNotBlank(param)) {
            REWRITE_SUFFIX=param;
        }

        param=config.getInitParameter("excludeUrlPattern");
        if(StringUtils.isNotBlank(param)) {
            excludedUrlPatterns=param;
        }

        excludedUrls=FilterUtil.spliteUrlPatterns(excludedUrlPatterns);
    }
}
