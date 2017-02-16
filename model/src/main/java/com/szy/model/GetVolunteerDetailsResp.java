package com.szy.model;

import com.szy.RespEnum;
import com.szy.Response;
import com.szy.db.model.VolunteerDbo;

/**
 * Created by shizhouyong on 2017/2/16.
 */
public class GetVolunteerDetailsResp extends Response{

    private long number;
    private String majors;
    private long createTime;
    private long updateTime;
    private int status;

    public GetVolunteerDetailsResp(VolunteerDbo dbo) {
        this.number = dbo.getNumber();
        this.majors = dbo.getMajors();
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

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
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
