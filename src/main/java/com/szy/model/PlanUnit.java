package com.szy.model;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class PlanUnit {

    private int id;
    private String name;
    private int classAmount;
    private int stuAmount;
    private int realStuAmount;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassAmount() {
        return classAmount;
    }

    public void setClassAmount(int classAmount) {
        this.classAmount = classAmount;
    }

    public int getStuAmount() {
        return stuAmount;
    }

    public void setStuAmount(int stuAmount) {
        this.stuAmount = stuAmount;
    }

    public int getRealStuAmount() {
        return realStuAmount;
    }

    public void setRealStuAmount(int realStuAmount) {
        this.realStuAmount = realStuAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
