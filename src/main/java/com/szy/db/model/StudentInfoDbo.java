package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/1/26.
 */
public class StudentInfoDbo {

    private long number;
    private String name;
    private String telephone;
    private int category;
    private String originalClass;
    private int presentClass;
    private String sex;
    private String dorm;
    private String note;
    private double GPA;
    private double realGPA;
    private String stuFrom;
    private int division;
    private int entranceScore;
    private int admissionScore;
    private double gradeOne;
    private double gradeTwo;
    private double totalGrade;
    private int rank;
    private int createUser;
    private long createTime;
    private long updateTime;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getOriginalClass() {
        return originalClass;
    }

    public void setOriginalClass(String originalClass) {
        this.originalClass = originalClass;
    }

    public int getPresentClass() {
        return presentClass;
    }

    public void setPresentClass(int presentClass) {
        this.presentClass = presentClass;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public double getRealGPA() {
        return realGPA;
    }

    public void setRealGPA(double realGPA) {
        this.realGPA = realGPA;
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

    public int getEntranceScore() {
        return entranceScore;
    }

    public void setEntranceScore(int entranceScore) {
        this.entranceScore = entranceScore;
    }

    public int getAdmissionScore() {
        return admissionScore;
    }

    public void setAdmissionScore(int admissionScore) {
        this.admissionScore = admissionScore;
    }

    public double getGradeOne() {
        return gradeOne;
    }

    public void setGradeOne(double gradeOne) {
        this.gradeOne = gradeOne;
    }

    public double getGradeTwo() {
        return gradeTwo;
    }

    public void setGradeTwo(double gradeTwo) {
        this.gradeTwo = gradeTwo;
    }

    public double getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(double totalGrade) {
        this.totalGrade = totalGrade;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
