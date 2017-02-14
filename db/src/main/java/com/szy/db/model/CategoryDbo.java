package com.szy.db.model;

import com.szy.db.model.SystemInfo;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class CategoryDbo extends SystemInfo{

    private String description;
    private int status;

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
