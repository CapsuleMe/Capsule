package com.capsule.android.db;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.capsule.android.MyApplication;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public abstract class DataManager<T> {
	
	protected static BaseSqliteOpenHelper mDBHelper = new BaseSqliteOpenHelper(MyApplication.CONTEXT);
	protected static ConnectionSource mConnect = null;
	
	private Class<T> mClazz = null;
	private Dao<T,?> mDao = null;
		
	public DataManager(Class<T> clazz) throws SQLException
	{
		mClazz = clazz;
		createTalbe();
	}
	
	public abstract List<T> query(PreparedQuery<T> q)throws SQLException;
	public abstract Iterator<T> read()throws SQLException;
	public abstract int create(T obj)throws SQLException;
	public abstract int update(T obj)throws SQLException;
	public abstract int delete(T obj)throws SQLException;
	public abstract CreateOrUpdateStatus createOrUpdate(T obj)throws SQLException;
	
	
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
