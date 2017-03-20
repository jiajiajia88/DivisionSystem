package com.szy.model;

/**
 * Created by shizhouyong on 2017/3/19.
 */
public class GetNewClassListReq {

    private int grade;
    private int category;
    private int newMajor;

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

    public int getNewMajor() {
        return newMajor;
    }

    public void setNewMajor(int newMajor) {
        this.newMajor = newMajor;
    }
}
