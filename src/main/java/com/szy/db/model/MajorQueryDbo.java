package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class MajorQueryDbo extends SystemInfo{

    private String category;
    private String description;
    private int status;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
