package com.szy.model;

import com.szy.Response;

public class UserLoginResp extends Response {
    private long number;
    private String name;
    private String telePhone;
    private String session;
    private int limit;
    private long expire;

    public UserLoginResp(long number, String name, String telePhone, String session, int limit, long expire) {
        this.number = number;
        this.name = name;
        this.telePhone = telePhone;
        this.session = session;
        this.limit = limit;
        this.expire = expire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
