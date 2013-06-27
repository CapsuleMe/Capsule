package com.capsule.android.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.capsule.model.Friend;
import com.capsule.model.Location;

public class FriendManager extends ObservableCollcetion<Friend> {

	@Override
	protected Collection<Friend> initCollection() {
		// TODO Auto-generated method stub
		return new ArrayList<Friend>();
	}

	public void load(){
		initTest();
	}

	public void save(){


	}

	public void sync(){

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


	private void initTest()
	{
		for(int i=0; i<10; i++){
			this.collection.add(getFriend(true));
		}

		for(int i=0; i<10; i++){
			this.collection.add(getFriend(false));
		}

	}

	private Friend getFriend(boolean isFriend)
	{
		Location location = new Location();
    	location.setAddress("北京市海淀区中关村东路1号赛尔大厦22层");
    	location.setLatitude(123);
    	location.setLongitude(123);
    	location.setDateTime(new Date());
    	
    	Friend f = new Friend();
     	f.setId("1");
    	f.setName(isFriend?"宪超":"卡比兽");
    	f.setFtype(isFriend?Friend.FRIEND:Friend.VIP);
    	f.setNumber("186111111");
    	f.setPassword("ttt");
    	f.setLocation(location);
    	
    	return f;
	}

}