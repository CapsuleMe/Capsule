package com.capsule.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

public class BaseSqliteOpenHelper extends OrmLiteSqliteOpenHelper {

	public static final String DATABASE_NAME = "capsule.sqlite";
	public static final int DATABASE_VERSION = 1;	

	private Context mContext = null;
	
	public BaseSqliteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		mContext = context;
	}

	public Context getContext() {
		return mContext;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connect) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
	}
	
	
	
}
