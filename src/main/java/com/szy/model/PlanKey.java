package com.szy.model;

/**
 * Created by shizhouyong on 2017/3/11.
 */
public class PlanKey {

    private int grade;
    private int category;

    public PlanKey(int grade, int category) {
        this.grade = grade;
        this.category = category;
    }

    public int getGrade() {

        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanKey planKey = (PlanKey) o;

        return grade == planKey.grade && category == planKey.category;
    }

    @Override
    public int hashCode() {
        int result = grade;
        result = 31 * result + category;
        return result;
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
}
