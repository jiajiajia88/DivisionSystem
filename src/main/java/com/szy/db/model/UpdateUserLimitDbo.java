package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/3/14.
 */
public class UpdateUserLimitDbo {

    private long number;
    private int limit;


    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public UpdateUserLimitDbo(long number, int limit) {
        this.number = number;
        this.limit = limit;
    }

    public int getLimit() {

        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
