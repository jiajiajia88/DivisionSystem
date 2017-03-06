package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class MajorDbo extends SystemInfo{

    private int category;
    private String description;
    private int status;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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
