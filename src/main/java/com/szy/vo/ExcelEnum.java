package com.szy.vo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shizhouyong on 2017/3/16.
 */
public enum ExcelEnum {

    SHUNT_RESULT(new String[]{
            "学号",
            "姓名",
            "年级",
            "手机号",
            "所属大类",
            "原班级",
            "性别",
            "平均学分绩点",
            "平均学分绩点*70%",
            "生源地",
            "文理科",
            "高考成绩",
            "生源省高考录取线",
            "高考成绩/生源省高考录取线",
            "30%*高考成绩/生源省高考录取线",
            "总成绩=70%*平均学分绩点 + 30%*高考成绩/生源省高考录取线",
            "排名",
            "新班级",
            "新专业",
            "寝室",
            "备注"});

    private String[] head;

    private ExcelEnum(String[] head) {
        this.head = head;
    }

    public List<String> getHeadList() {
        return Arrays.asList(head);
    }

}
