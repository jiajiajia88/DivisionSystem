package com.szy.model;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class UpdatePlanReq {

    private int id;
    private int grade;
    private int category;
    private int majorAmount;
    private long endTime;
    private List<PlanUnit> details;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getMajorAmount() {
        return majorAmount;
    }

    public void setMajorAmount(int majorAmount) {
        this.majorAmount = majorAmount;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<PlanUnit> getDetails() {
        return details;
    }

    public void setDetails(List<PlanUnit> details) {
        this.details = details;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
