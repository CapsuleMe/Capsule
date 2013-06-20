package com.capsule.android.rest.api;

import java.util.Date;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class LocationClient extends RestClient {

	public LocationClient(RestTemplate tmp) {
		super(tmp);
		// TODO Auto-generated constructor stub
	}

	public int upload(double la, double lo, double ac, Date date) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("la", String.valueOf(la));
		mvm.add("lo", String.valueOf(lo));
		mvm.add("ac", String.valueOf(ac));
		mvm.add("date", date.toString());

		return template.postForObject(GetURL("loc", "rec"), mvm, int.class);
	}
}
