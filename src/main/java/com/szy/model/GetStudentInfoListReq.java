package com.szy.model;

import com.szy.db.model.StuInfoFilter;

/**
 * Created by shizhouyong on 2017/2/20.
 */
public class GetStudentInfoListReq {

    private StuInfoFilter filter;
    private Order order;

    public StuInfoFilter getFilter() {
        return filter;
    }

    public void setFilter(StuInfoFilter filter) {
        this.filter = filter;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
