package com.szy.model;

import com.szy.Response;
import com.szy.db.model.SystemInfo;

import java.util.List;

/**
 * Created by shizhouyong on 2017/1/10.
 */
public class GetSystemInfoListResp extends Response{

    private List<SystemInfo> systemInfoList;

    public GetSystemInfoListResp(List<SystemInfo> systemInfoList) {
        this.systemInfoList = systemInfoList;
    }

    public List<SystemInfo> getSystemInfoList() {
        return systemInfoList;
    }

    public void setSystemInfoList(List<SystemInfo> systemInfoList) {
        this.systemInfoList = systemInfoList;
    }
}
