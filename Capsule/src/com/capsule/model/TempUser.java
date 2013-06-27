package com.capsule.model;

public class TempUser extends User {

	private boolean isFriend = false;
	private boolean isUser = false;
	
	public boolean isFriend() {
		return isFriend;
	}
	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	
	
}
