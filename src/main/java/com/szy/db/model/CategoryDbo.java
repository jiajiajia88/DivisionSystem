package com.szy.db.model;

import com.szy.model.SystemInfo;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class CategoryDbo extends SystemInfo{

    private int stuAmount;
    private int status;

    public int getStuAmount() {
        return stuAmount;
    }

    public void setStuAmount(int stuAmount) {
        this.stuAmount = stuAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
