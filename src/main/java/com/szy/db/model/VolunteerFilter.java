package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class VolunteerFilter {

    private long number;
    private int status;
    private int category;
    private int firstChoose;
    private int secondChoose;
    private int thirdChoose;
    private String name;
    private String originalClass;
    private String sex;
    private String stuFrom;
    private int division;
    private Time createTime;
    private Time updateTime;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getFirstChoose() {
        return firstChoose;
    }

    public void setFirstChoose(int firstChoose) {
        this.firstChoose = firstChoose;
    }

    public int getSecondChoose() {
        return secondChoose;
    }

    public void setSecondChoose(int secondChoose) {
        this.secondChoose = secondChoose;
    }

    public int getThirdChoose() {
        return thirdChoose;
    }

    public void setThirdChoose(int thirdChoose) {
        this.thirdChoose = thirdChoose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalClass() {
        return originalClass;
    }

    public void setOriginalClass(String originalClass) {
        this.originalClass = originalClass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStuFrom() {
        return stuFrom;
    }

    public void setStuFrom(String stuFrom) {
        this.stuFrom = stuFrom;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }

    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }

    public Time getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Time updateTime) {
        this.updateTime = updateTime;
    }
}
