package com.szy.model;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class SaveVolunteerReq {

    private long number;
    private int firstChoose;
    private int secondChoose;
    private int thirdChoose;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getFirstChoose() {
        return firstChoose;
    }

    public void setFirstChoose(int firstChoose) {
        this.firstChoose = firstChoose;
    }

    public int getSecondChoose() {
        return secondChoose;
    }

    public void setSecondChoose(int secondChoose) {
        this.secondChoose = secondChoose;
    }

    public int getThirdChoose() {
        return thirdChoose;
    }

    public void setThirdChoose(int thirdChoose) {
        this.thirdChoose = thirdChoose;
    }
}
