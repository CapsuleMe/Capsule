package com.capsule.service.impl;

import com.capsule.dao.OnlineUserDAO;
import com.capsule.exception.CapsuleException;
import com.capsule.model.OnlineUser;
import com.capsule.service.OnlineUserService;

public class OnlineUserServiceImpl implements OnlineUserService{

    private OnlineUserDAO onlineUserDAO;
    
    public OnlineUser getOnlineUser(String ticket) throws CapsuleException {
        return onlineUserDAO.getOnlineUser(ticket);
    }

    public void setOnlineUserDAO(OnlineUserDAO onlineUserDAO) {
        this.onlineUserDAO=onlineUserDAO;
    }
    
    
}
