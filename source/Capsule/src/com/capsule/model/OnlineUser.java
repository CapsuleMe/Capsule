package com.capsule.model;

import java.util.Date;

public class OnlineUser {

    private String ticket;

    private String userId;

    private Date loginDate;

    private String ip;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket=ticket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId=userId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate=loginDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip=ip;
    }

}
