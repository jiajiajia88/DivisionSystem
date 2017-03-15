package com.szy.model;

import java.util.List;

/**
 *
 * Created by shizhouyong on 2017/1/24.
 */
public class AddPlanReq {

    private int grade;
    private int category;
    private long endTime;
    private List<PlanUnit> details;
    private String remarks;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<PlanUnit> getDetails() {
        return details;
    }

    public void setDetails(List<PlanUnit> details) {
        this.details = details;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
