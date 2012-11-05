package com.capsule.common;

import android.content.Context;
import android.content.Intent;

import com.capsule.android.LoginActivity;
import com.capsule.android.MainActivity;
import com.capsule.android.RegisterActivity;

public class Navigator {

	//Add your new Activity SEQ value there
	public final static int MainActivitySEQ = 0;
	public final static int LoginActivitySEQ = 1;
	public final static int RegistActivitySEQ = 2;
	public final static int ForgertPasswordSEQ = 3;
	public final static int FriendListActivitySEQ = 10;
	
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
	public void switchTo(int seq, Intent src){
		switch(seq) {
		    case MainActivitySEQ:
		        swtichTo(MainActivity.class, src);
		    case LoginActivitySEQ:
                swtichTo(LoginActivity.class, src);
                break;
            case RegistActivitySEQ:
                swtichTo(RegisterActivity.class, src);
            default:
                break;
        }
	    return;
	}
	
}
