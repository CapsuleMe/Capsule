package com.capsule.android.rest.extend;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyClientHttpRequestInterceptor implements
		ClientHttpRequestInterceptor {

	public static String SessionCookie = null;

	private static final String SET_COOKIE = "set-cookie";
	private static final String COOKIE = "cookie";

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] byteArray,
			ClientHttpRequestExecution execution) throws IOException {

		List<String> cookies = request.getHeaders().get(COOKIE);
		// if the header doesn't exist, add any existing, saved cookies
		if (cookies == null) {
			request.getHeaders().add(COOKIE, SessionCookie);
		}
		// execute the request
		ClientHttpResponse response = execution.execute(request, byteArray);

		// pull any cookies off and store them
		cookies = response.getHeaders().get(SET_COOKIE);
		if (cookies != null) {
			for (String cookie : cookies) {
				SessionCookie = cookie;
			}
		}
		return response;
	}

}
