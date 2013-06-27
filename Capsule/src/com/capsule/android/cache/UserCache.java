package com.capsule.android.cache;

import java.util.Observable;

import com.capsule.model.User;

public class UserCache extends Observable{
	private User user = null;
	
	public User LoadUser(){	
		//Get From DB
		return user;
	}
	
	public void UpdateUser(User user){
		user = new User();
		notify();
	}
	
	public void UpdateUser(com.capsule.android.rest.models.User restUser){
		if(user == null){
			user = new User();
		}
		
		user.setId(restUser.get_id());
		user.setName(restUser.getName());
		user.setPassword(restUser.getPassword());
		
		notify();
	}
}
