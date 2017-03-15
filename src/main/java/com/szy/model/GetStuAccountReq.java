package com.szy.model;

/**
 * Created by shizhouyong on 2017/3/13.
 */
public class GetStuAccountReq {

    private Filter filter;
    private Order order;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
