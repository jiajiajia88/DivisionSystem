package com.szy.model;

import com.szy.db.model.Order;
import com.szy.db.model.VolunteerFilter;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetVolunteersReq {

    private VolunteerFilter filter;
    private Order order;

    public VolunteerFilter getFilter() {
        return filter;
    }

    public void setFilter(VolunteerFilter filter) {
        this.filter = filter;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
