package com.capsule.common;

import android.content.Context;
import android.content.Intent;

import com.capsule.android.LoginActivity;

public class Navigator {

	//Add your new Activity SEQ value there
	public static int MainActivitySEQ = 0;
	public static int LoginActivitySEQ = 1;
	public static int RegistActivitySEQ = 2;
	public static int ForgertPasswordSEQ = 3;
	public static int FriendListActivitySEQ = 10;
	
	private Context myContext = null;
	
	public Navigator(Context context)
	{
		myContext = context;
	}
	
	/**
	 * @param The component class that is to be used for the intent
	 */
	public void swtichTo(Class<?> cls)
	{
		swtichTo(cls,null);
	}
	
	/**
	 * @param cls The component class that is to be used for the intent
	 * @param src Copy all extras in "src" to new intent
	 */
	public void swtichTo(Class<?> cls,Intent src)
	{
      Intent intent = new Intent(myContext,cls);
      if(src != null)
      {
    	  intent.putExtras(src);
      }
      myContext.startActivity(intent);
	}
	
	/**
	 * @param seq Activity SEQ number, you can use Navigator.MainActivtySEQ to switch
	 */
	public void swtichTo(int seq)
	{
		switchTo(seq,null);
	}
	
	/**
	 * @param seq Activity SEQ number, you can use Navigator.MainActivtySEQ to switch
	 * @param src Copy all extras in "src" to new intent
	 */
	public void switchTo(int seq, Intent src)
	{
		if(seq == LoginActivitySEQ )
		{
			swtichTo(LoginActivity.class,src);
			return;
		}
	}
	
}
