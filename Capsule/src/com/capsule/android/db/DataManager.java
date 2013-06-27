package com.capsule.android.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.Context;

public abstract class DataManager<T> {
	
	protected Context mContext = null;
	protected BaseSqliteOpenHelper mDBHelper = null;
	
	private ConnectionSource mConnect = null;
	private Class<T> mClazz = null;
	private Dao<T,?> mDao = null;
	
	public DataManager(Context context,Class<T> clazz)
	{
		context = mContext;
		mDBHelper = new BaseSqliteOpenHelper(context);
		mClazz = clazz;
	}
	
	public abstract List<T> query(PreparedQuery<T> q)throws SQLException;
	public abstract int create(T obj)throws SQLException;
	public abstract int update(T obj)throws SQLException;
	public abstract int delete(T obj)throws SQLException;
	
	
	protected ConnectionSource getConnection()
	{
		if(mConnect == null)
		{
			mConnect = mDBHelper.getConnectionSource();
		}
		
		return mConnect;
	}
	protected Dao<T,?> getDao() throws SQLException
	{
		if(mDao == null)
		{
			mDao = mDBHelper.getDao(mClazz);
		}
		return mDao;
	}
	protected int createTalbe() throws SQLException
	{
		return TableUtils.createTableIfNotExists(getConnection(), mClazz);
	}
	
	
}
