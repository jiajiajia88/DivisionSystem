package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/3/12.
 */
public class PlanOperDbo {

    private int id;
    private int oper;

    public PlanOperDbo(int id, int oper) {
        this.id = id;
        this.oper = oper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOper() {
        return oper;
    }

    public void setOper(int oper) {
        this.oper = oper;
    }
}
