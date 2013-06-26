package com.capsule.android.rest.api;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.capsule.android.rest.models.User;

public class UserClient extends RestClient {

	public UserClient(RestTemplate tmp) {
		super(tmp);
	}

	public User register(String name, String password) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("name", name);
		mvm.add("password", password);

		return template
				.postForObject(GetURL("users", "reg"), mvm, User[].class)[0];
	}

	// 0 success 1 no user 2 wrong password
	public int login(String uid, String pwd) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", uid);
		mvm.add("pwd", pwd);
		return template.postForObject(GetURL("users", "login"), mvm, int.class);
	}

	// 0 success 1 no user 2 wrong password
	public int loginByNumber(String number, String pwd) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("number", number);
		mvm.add("pwd", pwd);

		return template
				.postForObject(GetURL("users", "login2"), mvm, int.class);
	}

	public int logout() {
		return template.postForObject(GetURL("users", "logout"), null,
				int.class);
	}

	public User getCurrentUser() {
		return template.postForObject(GetURL("users", "get"), null, User.class);
	}

	public User getUser(String uid) {
		MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
		mvm.add("id", uid);

		return template
				.postForObject(GetURL("users", "get"), mvm, User[].class)[0];
	}
}
