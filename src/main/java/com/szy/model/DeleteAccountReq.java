package com.szy.model;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/14.
 */
public class DeleteAccountReq {

    private List<Long> numberList;

    public List<Long> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Long> numberList) {
        this.numberList = numberList;
    }
}
