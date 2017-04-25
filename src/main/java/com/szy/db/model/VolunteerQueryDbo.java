package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/25.
 */
public class VolunteerQueryDbo {

    private long number;
    private String name;
    private int firstChooseId;
    private int secondChooseId;
    private int thirdChooseId;
    private String firstChoose;
    private String secondChoose;
    private String thirdChoose;
    private long createTime;
    private long updateTime;
    private long commitTime;
    private int rank;
    private int shuntStatus = 0;
    private int status;

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

    public int getFirstChooseId() {
        return firstChooseId;
    }

    public void setFirstChooseId(int firstChooseId) {
        this.firstChooseId = firstChooseId;
    }

    public int getSecondChooseId() {
        return secondChooseId;
    }

    public void setSecondChooseId(int secondChooseId) {
        this.secondChooseId = secondChooseId;
    }

    public int getThirdChooseId() {
        return thirdChooseId;
    }

    public void setThirdChooseId(int thirdChooseId) {
        this.thirdChooseId = thirdChooseId;
    }

    public String getFirstChoose() {
        return firstChoose;
    }

    public void setFirstChoose(String firstChoose) {
        this.firstChoose = firstChoose;
    }

    public String getSecondChoose() {
        return secondChoose;
    }

    public void setSecondChoose(String secondChoose) {
        this.secondChoose = secondChoose;
    }

    public String getThirdChoose() {
        return thirdChoose;
    }

    public void setThirdChoose(String thirdChoose) {
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getShuntStatus() {
        return shuntStatus;
    }

    public void setShuntStatus(int shuntStatus) {
        this.shuntStatus = shuntStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
