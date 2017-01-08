package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/8.
 */
public class PlanDbo {

    private Integer id;
    private Integer grade;
    private Integer species;
    private Integer studentAmount;
    private Integer majorAmount;
    private long createTime;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSpecies() {
        return species;
    }

    public void setSpecies(Integer species) {
        this.species = species;
    }

    public Integer getStudentAmount() {
        return studentAmount;
    }

    public void setStudentAmount(Integer studentAmount) {
        this.studentAmount = studentAmount;
    }

    public Integer getMajorAmount() {
        return majorAmount;
    }

    public void setMajorAmount(Integer majorAmount) {
        this.majorAmount = majorAmount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
