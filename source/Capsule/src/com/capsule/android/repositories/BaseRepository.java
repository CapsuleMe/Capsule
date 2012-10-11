package com.capsule.android.repositories;

import android.content.Context;

public abstract class BaseRepository {

	private Context myContext = null;
	
	public BaseRepository(Context context)
	{
		myContext = context;
	}
}
