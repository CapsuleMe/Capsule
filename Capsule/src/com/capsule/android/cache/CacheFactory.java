package com.capsule.android.cache;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;

public class CacheFactory implements ICache{
	
	final static String USER_CACHE_KEY = "USER";
	
	Hashtable<String,ICache> cacheTable = null;
	
	public CacheFactory(){
		cacheTable = new Hashtable<String,ICache>();
		
		try {
			cacheTable.put(USER_CACHE_KEY, new UserCache());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(){
		Iterator<ICache> it = cacheTable.values().iterator();
		while(it.hasNext()){
			it.next().load();
		}
	}
	
	public void save(){
		Iterator<ICache> it = cacheTable.values().iterator();
		while(it.hasNext()){
			it.next().save();
		}
	}
	
	public UserCache getUser(){
		return (UserCache) cacheTable.get(USER_CACHE_KEY);
	}
}
