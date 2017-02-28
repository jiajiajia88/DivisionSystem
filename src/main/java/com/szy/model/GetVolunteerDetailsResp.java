package com.szy.model;

import com.szy.Response;
import com.szy.db.model.VolunteerDbo;

/**
 * Created by shizhouyong on 2017/2/16.
 */
public class GetVolunteerDetailsResp extends Response{

    private long number;
    private int firstChoose;
    private int secondChoose;
    private int thirdChoose;
    private long createTime;
    private long updateTime;
    private int status;

    public GetVolunteerDetailsResp(VolunteerDbo dbo) {
        this.number = dbo.getNumber();
        this.firstChoose = dbo.getFirstChoose();
        this.secondChoose = dbo.getSecondChoose();
        this.thirdChoose = dbo.getThirdChoose();
        this.createTime = dbo.getCreateTime();
        this.updateTime = dbo.getUpdateTime();
        this.status = dbo.getStatus();
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
