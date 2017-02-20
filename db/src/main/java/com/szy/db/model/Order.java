package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class Order {

    private int from = 0;
    private int size = 15;
    private String item = "number";
    private String sort = "asc";

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
