package com.szy.filter;

import com.szy.RespEnum;
import com.szy.model.Account;
import com.szy.service.IUserService;
import com.szy.util.UserLimitUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 学生角色过滤器
 * Created by shizhouyong on 2017/1/7.
 */

//@WebFilter(filterName = "StudentFilter", urlPatterns = {"/jg/v/*"})
public class StudentFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        Account account = (Account)request.getSession().getAttribute("account");

        if(account != null) {
            if (UserLimitUtil.verify(account.getLimit(), UserLimitUtil.USER_STUDENT))
                chain.doFilter(req, res);
            else
                res.getWriter().write(RespEnum.NO_ACCESS.toString());
        } else {
            res.getWriter().write(RespEnum.NOT_LOGIN.toString());
        }
    }

    @Override
    public void destroy() {

    }
}