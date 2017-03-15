package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class PlanQueryDbo {

    private int id;
    private String grade;
    private String category;
    private int gradeId;
    private int categoryId;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
