package com.capsule.android.rest.api;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.capsule.android.rest.models.FriendMessage;
import com.capsule.android.rest.models.Friends;

public class FriendClient extends RestClient {

	public FriendClient(RestTemplate tmp) {
		super(tmp);
		// TODO Auto-generated constructor stub
	}

	public Friends getAll() {
		return template.postForObject(GetURL("friend", "list"), null,
				Friends[].class)[0];
	}

	public FriendMessage addNear(String uid) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", uid);
		return template.postForObject(GetURL("friend", "near"), mvm,
				FriendMessage[].class)[0];

	}

	public FriendMessage addNormal(String uid) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", uid);
		return template.postForObject(GetURL("friend", "normal"), mvm,
				FriendMessage[].class)[0];
	}

	public int confirmAdd(String mid) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", mid);
		return template.postForObject(GetURL("friend", "confirm"), mvm,
				int.class);
	}

	public int cancelAdd(String mid) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", mid);
		return template.postForObject(GetURL("friend", "cancel"), mvm,
				int.class);
	}

	public int remove(String uid) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", uid);
		return template.postForObject(GetURL("friend", "remove"), mvm,
				int.class);
	}

}
