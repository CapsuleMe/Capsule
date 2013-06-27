package com.capsule.dao.impl;


import java.sql.SQLException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.capsule.dao.OnlineUserDAO;
import com.capsule.exception.CapsuleException;
import com.capsule.model.OnlineUser;


public class OnlineUserDAOImpl extends SqlMapClientDaoSupport implements OnlineUserDAO{

    public OnlineUser getOnlineUser(String ticket) throws CapsuleException{
        try {
            return (OnlineUser)this.getSqlMapClient().queryForObject("ONLINE_USER.getOnlineUser", ticket);
        } catch(SQLException e) {
            throw new CapsuleException(e);
        }
    }
}
