package com.szy.filter;

import com.szy.RespEnum;
import com.szy.session.LocalUtil;
import com.szy.session.Session;
import com.szy.util.CacheUtil;
import com.szy.util.UserLimitUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 学生角色过滤器
 * Created by shizhouyong on 2017/1/7.
 */

@WebFilter(filterName = "StudentFilter", urlPatterns = {"/jg/v/stu/*"})
public class StudentFilter implements Filter {

    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            String ss = req.getParameter("ss");
            if (ss == null) {
                res.getWriter().write(RespEnum.PARAMETER_MiSS.toString());
                return;
            }
            if (ss.length() != 40) {
                res.getWriter().write(RespEnum.NOT_LOGIN.toString());
                return;
            }
            String key = ss.substring(0, 32);
            Session session = cacheUtil.getSession(key);
            if (session == null || !UserLimitUtil.verify(session.getLimit(), UserLimitUtil.USER_STUDENT)) {
                res.getWriter().write(RespEnum.NO_ACCESS.toString());
                return;
            }
            session.setKey(key);
            LocalUtil.setSession(session);
            chain.doFilter(req, res);
        } catch (Exception e) {
            res.getWriter().write(RespEnum.UNKNOWN_ERROR.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}