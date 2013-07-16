package com.capsule.android.cache;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Observable;

import com.capsule.android.MyApplication;
import com.capsule.android.db.UserDataManager;
import com.capsule.model.User;

public class UserCache extends Observable implements ICache{
	
	private User user = null;
	private UserDataManager userMgr = null;
	
	public UserCache() throws SQLException{
		
		userMgr = new UserDataManager(User.class);
	}
	
	@Override
	public void load() {
		// TODO Auto-generated method stub
		try {
			Iterator<User> it = userMgr.read();
			if(it.hasNext()){
				user = it.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		try {
			userMgr.createOrUpdate(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public User getUser(){	
		//Get From DB
		return user;
	}
	
	public void updateUser(User user){
		this.user = user;
		this.save();
		notifyObservers(this);
	}
	
	public void updateUser(com.capsule.android.rest.models.User restUser){
		if(user == null){
			user = new User();
		}
		
		user.setId(restUser.get_id());
		user.setName(restUser.getName());
		user.setPassword(restUser.getPassword());
		user.setHead(restUser.getHead());
		user.setMale(restUser.isMale());
		user.setNumber(restUser.getNumber());
		user.setRole(restUser.getRole());
		
		this.save();
		notifyObservers(this);
	}

	
}
