package com.szy.model;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public class UserLoginReq {

    private long number;
    private String password;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
