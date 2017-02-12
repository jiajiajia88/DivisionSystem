package com.szy.model;

import com.szy.db.model.GetPlanItems;

/**
 * Created by shizhouyong on 2017/1/24.
 */
public class GetPlansReq {

    private GetPlanItems items;

    public GetPlanItems getItems() {
        return items;
    }

    public void setItems(GetPlanItems items) {
        this.items = items;
    }
}
