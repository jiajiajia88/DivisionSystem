package com.szy.vo;

/**
 * Created by shizhouyong on 2017/1/6.
 */
public class Account {

    private long number;
    private int limit;
    private long loginTime;

    public Account() {
    }

    public Account(long number, int limit, long loginTime) {
        this.number = number;
        this.limit = limit;
        this.loginTime = loginTime;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }
}
