package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class OperDbo {

    private int id;
    private long operTime;
    private long user;
    private String scope;     //作用域：{GRADE，MAJOR, PLAN, POSITION, SPECIES, DIVISION}
    private String description;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOperTime() {
        return operTime;
    }

    public void setOperTime(long operTime) {
        this.operTime = operTime;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
