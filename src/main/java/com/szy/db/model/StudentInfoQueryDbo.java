package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class StudentInfoQueryDbo {

    private long number;
    private String name;
    private String telephone;
    private String grade;
    private int gradeId;
    private String category;         //大类具体名称
    private int categoryId;
    private String originalClass;
    private int presentClass;        //班级具体名称
    private String sex;              //性别
    private String dorm;
    private String note;
    private double GPA;
    private double realGPA;
    private String stuFrom;
    private String division;            //文理科描述
    private int entranceScore;
    private int admissionScore;
    private double gradeOne;
    private double gradeTwo;
    private double totalGrade;
    private int rank;
    private String createUser;       //创建者名字
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
        switch (sex) {
            case "F":
                this.sex = "女";
                break;
            case "M":
                this.sex = "男";
                break;
        }
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

    public String getDivision() {
        return division;
    }

    public void setDivision(int division) {
        switch (division) {
            case 1:
                this.division = "文科";
                break;
            case 2:
                this.division = "理科";
                break;
        }
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
