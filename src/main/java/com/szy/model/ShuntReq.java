package com.szy.model;

import com.szy.vo.Order;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public class ShuntReq {

    private int category;
    private int grade;
    private Order order;

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
