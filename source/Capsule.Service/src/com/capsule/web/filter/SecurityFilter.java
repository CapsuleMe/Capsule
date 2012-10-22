package com.capsule.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.capsule.Constants;
import com.capsule.common.context.GlobeContext;
import com.capsule.common.util.FilterUtil;
import com.capsule.common.util.MessageDigestUtil;
import com.capsule.common.util.ParameterUtil;
import com.capsule.common.util.RequestUtil;
import com.capsule.dao.OnlineUserDAO;
import com.capsule.exception.CapsuleException;
import com.capsule.model.OnlineUser;
import com.capsule.service.OnlineUserService;

/**
 * 安全验证
 */
public class SecurityFilter implements Filter{

    private String onlineUserBeanName="onlineUserBean";
    
    private List<String> ignoreUrlPattern=null;
    
    private ServletContext servletContext;
    
    private OnlineUserService onlineUserService;
    
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException,
        ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        try {
            checkLogin(request);
            checkRequest(request);
        } catch(Exception e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }

    private void checkLogin(HttpServletRequest request) throws Exception {
        String servletPath=request.getServletPath();
        boolean isIgnore=FilterUtil.checkRequestUrl(servletPath, ignoreUrlPattern);
        if(!isIgnore){
            String encryptTicket=RequestUtil.getString(request, Constants.TICKET); // 加密的ticket
            OnlineUser onlineUser=onlineUserService.getOnlineUser(encryptTicket);
            request.setAttribute(Constants.ONLINE_USER, onlineUser);// 设置
        }
    }
    
    private void checkRequest(HttpServletRequest request) throws Exception {
        String sign=RequestUtil.getString(request, "sign");
        if(null == sign) {
            throw new CapsuleException("没有签名参数");
        }
        Map<String, String[]> params=new HashMap<String, String[]>(request.getParameterMap());
        String signData=ParameterUtil.getSignData(params);
        String tmp=MessageDigestUtil.getMD5(signData + Constants.SIGN_DATA_KEY);
        if(!sign.equals(tmp)){
            throw new CapsuleException("签名验证失败");
        }
    }
    
    @Override
    public void init(FilterConfig config) throws ServletException {
        servletContext=config.getServletContext();
        //获得初始化参数
        String param = config.getInitParameter("onlineUserBean");  
        if (StringUtils.isNotBlank(param)){
            onlineUserBeanName = param;
        }
        param = config.getInitParameter("ignoreUrlPattern"); 
        if (StringUtils.isNotBlank(param)){
            ignoreUrlPattern = FilterUtil.spliteUrlPatterns(param);
        }
        onlineUserService = (OnlineUserService) GlobeContext.getApplicationContext().getBean(onlineUserBeanName);
    }
    
    @Override
    public void destroy() {
    }

  
}
