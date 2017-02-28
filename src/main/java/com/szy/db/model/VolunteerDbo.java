package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/25.
 */
public class VolunteerDbo {

    private long number;
    private int firstChoose;
    private int secondChoose;
    private int thirdChoose;
    private long createTime;
    private long updateTime;
    private long commitTime;
    private int status;

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(long commitTime) {
        this.commitTime = commitTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
