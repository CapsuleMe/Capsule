package com.capsule.dao;

import com.capsule.exception.CapsuleException;
import com.capsule.model.OnlineUser;


public interface OnlineUserDAO {

    /**
     * 根据ticket获取在线用户
     * @param ticket
     * @return
     * @throws CapsuleException
     */
    OnlineUser getOnlineUser(String ticket) throws CapsuleException;
}
