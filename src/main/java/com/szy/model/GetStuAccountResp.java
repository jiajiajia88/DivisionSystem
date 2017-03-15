package com.szy.model;

import com.szy.Response;
import com.szy.db.model.StuAccountDbo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/3/13.
 */
public class GetStuAccountResp extends Response{

    private List<StuAccountDbo> accounts;
    private int total;

    public GetStuAccountResp(List<StuAccountDbo> accounts, int total) {
        this.accounts = accounts;
        this.total = total;
    }

    public List<StuAccountDbo> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<StuAccountDbo> accounts) {
        this.accounts = accounts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
