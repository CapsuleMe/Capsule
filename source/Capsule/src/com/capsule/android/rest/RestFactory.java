package com.capsule.android.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.capsule.android.rest.api.UserClient;
import com.capsule.android.rest.extend.MyClientHttpRequestInterceptor;

public class RestFactory {
	
	public static String BaseUrl = null;
	
	private static RestTemplate template = null;
	
	
	private static RestTemplate getRestTemplate(){
		if(template == null){
			template = new RestTemplate(true);
			
			//Enable session cookie saving
			List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
			interceptors.add(new MyClientHttpRequestInterceptor());
			template.setInterceptors(interceptors);
		}
		return template;
	}
	
	public static UserClient getUserClient(){
		UserClient client = new UserClient(getRestTemplate());
		client.setBaseURL(BaseUrl);
		
		return client;
	}
}
