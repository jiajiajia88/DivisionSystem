package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/3/13.
 */
public class StuAccountDbo {

    private long number;
    private String name;
    private String category;
    private String grade;
    private String originalClass;
    private String limitDescription;
    private int limit;
    private long createTime;
    private int status;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOriginalClass() {
        return originalClass;
    }

    public void setOriginalClass(String originalClass) {
        this.originalClass = originalClass;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getLimitDescription() {
        return limitDescription;
    }

    public void setLimitDescription(String limitDescription) {
        this.limitDescription = limitDescription;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
