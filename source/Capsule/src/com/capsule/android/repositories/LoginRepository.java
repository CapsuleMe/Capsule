package com.capsule.android.repositories;

import com.capsule.model.User;

import android.content.Context;

public class LoginRepository extends BaseRepository {

	public LoginRepository(Context context)
	{
		super(context);
	}
	
	public String[] getHistoryUserNumber()
	{
		throw new UnsupportedOperationException();
	}
	
	public String getLastUserNumber()
	{
		throw new UnsupportedOperationException();
	}
	
	public User login(String number,String password)
	{
		throw new UnsupportedOperationException();
	}
	
}
