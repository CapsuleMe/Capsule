package com.capsule.android.rest.api;

import org.springframework.web.client.RestTemplate;

import com.capsule.android.rest.models.FriendMessage;
import com.capsule.android.rest.models.SystemMessage;
import com.capsule.android.rest.models.UserMessage;

public class MessageClient extends RestClient {

	public MessageClient(RestTemplate tmp) {
		super(tmp);
		// TODO Auto-generated constructor stub
	}

	public SystemMessage[] getSystemMessages() {
		return template.postForObject(GetURL("msg", "sys"), null,
				SystemMessage[].class);
	}

	public FriendMessage[] getFriendMessages() {
		return template.postForObject(GetURL("msg", "friend"), null,
				FriendMessage[].class);
	}

	public UserMessage[] getUserMessages() {
		return template.postForObject(GetURL("msg", "user"), null,
				UserMessage[].class);
	}
}
