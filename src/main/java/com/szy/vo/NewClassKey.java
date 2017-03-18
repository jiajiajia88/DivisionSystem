package com.szy.vo;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public class NewClassKey {

    private int grade;
    private int index;

    public NewClassKey(int grade, int index) {
        this.grade = grade;
        this.index = index;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewClassKey that = (NewClassKey) o;

        if (grade != that.grade) return false;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        int result = grade;
        result = 31 * result + index;
        return result;
    }
}
