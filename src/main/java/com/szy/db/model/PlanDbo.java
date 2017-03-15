package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class PlanDbo {

    private int id;
    private int grade;
    private int category;
    private int studentAmount;
    private int majorAmount;
    private String details;
    private long endTime;
    private long createTime;
    private long updateTime;
    private String remarks;
    private int status;

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

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public int getMajorAmount() {
        return majorAmount;
    }

    public void setMajorAmount(int majorAmount) {
        this.majorAmount = majorAmount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
