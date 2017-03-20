package com.szy.vo;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public class NewClassKey {

    private int major;
    private int index;

    public NewClassKey(int major, int index) {
        this.major = major;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewClassKey that = (NewClassKey) o;

        if (major != that.major) return false;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        int result = major;
        result = 31 * result + index;
        return result;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
