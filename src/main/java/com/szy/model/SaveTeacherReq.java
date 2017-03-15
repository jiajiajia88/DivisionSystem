package com.szy.model;

/**
 * Created by shizhouyong on 2017/3/8.
 */
public class SaveTeacherReq {

    private long number;
    private String name;
    private String position;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
