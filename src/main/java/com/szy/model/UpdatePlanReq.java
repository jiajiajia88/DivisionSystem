package com.szy.model;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class UpdatePlanReq {

    private int id;
    private int grade;
    private int category;
    private int studentAmount;
    private int majorAmount;
    private String details;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
