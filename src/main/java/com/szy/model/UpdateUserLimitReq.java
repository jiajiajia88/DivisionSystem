package com.szy.model;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/14.
 */
public class UpdateUserLimitReq {

    private List<Long> numberList;
    private int limit;

    public List<Long> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Long> numberList) {
        this.numberList = numberList;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
