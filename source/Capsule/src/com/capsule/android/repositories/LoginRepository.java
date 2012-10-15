package com.capsule.android.repositories;

import com.capsule.model.User;

import android.content.Context;

public class LoginRepository extends BaseRepository {

	public LoginRepository(Context context)
	{
		super(context);
	}
	
	public User loginAsLast() {
		String number = mySettingEditor.get("number", "");
		String password = mySettingEditor.get("password","");
		return login(number,password);
	}
	
	public String[] getHistoryUserNumber()
	{
		return null;
	}
	
	public String getLastUserNumber()
	{
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * @param number user number
	 * @param password password
	 * @return if verify failed, then return null
	 */
	public User login(String number,String password)
	{
		return null;
	}
	
}
