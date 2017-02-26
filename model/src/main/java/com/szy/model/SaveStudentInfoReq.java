package com.szy.model;

/**
 * Created by shizhouyong on 2017/2/17.
 */
public class SaveStudentInfoReq {

    private long number;
    private String name;
    private int category;
    private String originalClass;
    private String sex;
    private String dorm;
    private String note;
    private double GPA;
    private String stuFrom;
    private int division;
    private int entranceScore;
    private int admissionScore;

    public StudentInfo createStudentInfo() {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setNumber(number);
        studentInfo.setName(name);
        studentInfo.setCategory(category);
        studentInfo.setOriginalClass(originalClass);
        studentInfo.setSex(sex);
        studentInfo.setDorm(dorm);
        studentInfo.setNote(note);
        studentInfo.setGPA(GPA);
        studentInfo.setDivision(division);
        studentInfo.setStuFrom(stuFrom);
        studentInfo.setEntranceScore(entranceScore);
        studentInfo.setAdmissionScore(admissionScore);
        return studentInfo;
    }

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
}
