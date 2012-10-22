package com.capsule.service;

import com.capsule.exception.CapsuleException;
import com.capsule.model.OnlineUser;


public interface OnlineUserService {

    OnlineUser getOnlineUser(String ticket) throws CapsuleException;
}
