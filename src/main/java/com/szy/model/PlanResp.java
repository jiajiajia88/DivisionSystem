package com.szy.model;

import com.szy.vo.PlanUnit;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class PlanResp {

    private int id;
    private int gradeId;
    private int categoryId;
    private String grade;
    private String category;
    private int studentAmount;
    private int majorAmount;
    private List<PlanUnit> units;
    private long endTime;
    private long createTime;
    private long updateTime;
    private String remarks;
    private int status;

    public PlanResp(int id, int gradeId, int categoryId, String grade, String category, int studentAmount, int majorAmount, long endTime, long createTime, long updateTime, int status) {
        this.id = id;
        this.grade = grade;
        this.category = category;
        this.gradeId = gradeId;
        this.categoryId = categoryId;
        this.studentAmount = studentAmount;
        this.majorAmount = majorAmount;
        this.endTime = endTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(int studentAmount) {
        this.studentAmount = studentAmount;
    }

    public int getMajorAmount() {
        return majorAmount;
    }

    public List<PlanUnit> getUnits() {
        return units;
    }

    public void setUnits(List<PlanUnit> units) {
        this.units = units;
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
