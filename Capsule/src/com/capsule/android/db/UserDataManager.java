package com.capsule.android.db;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import android.content.Context;

import com.capsule.model.User;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.PreparedQuery;

public class UserDataManager extends DataManager<User> {

	public UserDataManager(Class<User> clazz)
			throws SQLException {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(User obj) throws SQLException {
		// TODO Auto-generated method stub
		return getDao().create(obj);
	}

	@Override
	public int update(User obj) throws SQLException {
		// TODO Auto-generated method stub
		return getDao().update(obj);
	}

	@Override
	public int delete(User obj) throws SQLException {
		// TODO Auto-generated method stub
		return getDao().delete(obj);
	}

	@Override
	public List<User> query(PreparedQuery<User> q) throws SQLException {
		// TODO Auto-generated method stub
		return getDao().query(q);
	}

	@Override
	public Iterator<User> read() throws SQLException {
		// TODO Auto-generated method stub
		return getDao().getWrappedIterable().iterator();
	}

	@Override
	public CreateOrUpdateStatus createOrUpdate(User obj) throws SQLException {
		// TODO Auto-generated method stub
		return getDao().createOrUpdate(obj);
	}
	


	
	
	
}
