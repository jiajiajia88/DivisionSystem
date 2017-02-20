package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetVolunteerItems {

    private int from = 0;
    private int size = 15;
    private String item = "number";
    private String sort = "asc";
    private int category;
    private long number;
    private long startCreateTime;
    private long endCreateTime;
    private long startUpdateTime;
    private long endUpdateTime;
    private int firstChoose;
    private int secondChoose;
    private int thirdChoose;
    private int status;
    private String name;
    private String originalClass;
    private String sex;
    private String stuFrom;
    private int division;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(long startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public long getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(long endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public long getStartUpdateTime() {
        return startUpdateTime;
    }

    public void setStartUpdateTime(long startUpdateTime) {
        this.startUpdateTime = startUpdateTime;
    }

    public long getEndUpdateTime() {
        return endUpdateTime;
    }

    public void setEndUpdateTime(long endUpdateTime) {
        this.endUpdateTime = endUpdateTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
