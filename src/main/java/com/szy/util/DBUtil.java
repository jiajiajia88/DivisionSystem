package com.szy.util;

import org.springframework.context.ApplicationContext;

/**
 * Created by shizhouyong on 2017/1/7.
 */
public class DBUtil {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        DBUtil.context = context;
    }

    public static <T> T getMapper(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
