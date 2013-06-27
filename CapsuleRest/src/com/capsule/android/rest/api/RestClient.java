package com.capsule.android.rest.api;

import org.springframework.web.client.RestTemplate;

public abstract class RestClient {

	protected final RestTemplate template;

	private String baseURL = "http://10.0.2.2:3000/";

	public RestClient(RestTemplate tmp) {
		template = tmp;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String GetURL(String route, String func) {
		if(baseURL.endsWith("/"))
			return baseURL + route + "/" + func;
		
		return baseURL + "/"+ route + "/" + func;
	}

}
