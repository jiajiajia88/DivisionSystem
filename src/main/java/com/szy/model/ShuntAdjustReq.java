package com.szy.model;

/**
 * Created by shizhouyong on 2017/3/19.
 */
public class ShuntAdjustReq {

    private long number;
    private int newMajor;
    private int newClass;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getNewMajor() {
        return newMajor;
    }

    public void setNewMajor(int newMajor) {
        this.newMajor = newMajor;
    }

    public int getNewClass() {
        return newClass;
    }

    public void setNewClass(int newClass) {
        this.newClass = newClass;
    }
}
