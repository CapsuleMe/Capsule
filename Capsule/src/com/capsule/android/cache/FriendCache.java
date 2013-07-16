package com.capsule.android.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.capsule.model.Friend;
import com.capsule.model.Location;

public class FriendCache extends ObservableCollcetion<Friend> implements ICache {

	@Override
	protected Collection<Friend> initCollection() {
		// TODO Auto-generated method stub
		return new ArrayList<Friend>();
	}

	public void load(){
		//Load from Local DB
		
		
	}

	public void save(){
		

	}

	public void update(){

	}

	public Collection<Friend> getAll(int ftype)
	{
		ArrayList<Friend> list = new ArrayList<Friend>();
		Iterator<Friend> it = this.iterator(); 
		while(it.hasNext())
		{
			Friend f = it.next();
			if(f.getFtype() == ftype){
				list.add(f);
			}
		}

		return list;
	}
}