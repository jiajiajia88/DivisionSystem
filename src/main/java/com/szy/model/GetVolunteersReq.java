package com.szy.model;

import com.szy.vo.Filter;
import com.szy.vo.Order;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetVolunteersReq {

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
