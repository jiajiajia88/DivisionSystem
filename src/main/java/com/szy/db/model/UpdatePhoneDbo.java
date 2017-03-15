package com.szy.db.model;

/**
 * Created by shizhouyong on 2017/3/8.
 */
public class UpdatePhoneDbo {

    private long number;
    private String telePhone;

    public UpdatePhoneDbo(long number, String telePhone) {
        this.number = number;
        this.telePhone = telePhone;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }
}
