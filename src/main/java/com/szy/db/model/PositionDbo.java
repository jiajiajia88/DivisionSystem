package com.szy.db.model;

import com.szy.model.SystemInfo;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class PositionDbo extends SystemInfo {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
