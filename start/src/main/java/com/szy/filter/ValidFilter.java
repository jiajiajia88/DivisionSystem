package com.szy.filter;

import com.szy.RespEnum;
import com.szy.model.Account;
import com.szy.service.IUserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by shizhouyong on 2017/1/7.
 */

@WebFilter(filterName = "ValidFilter", urlPatterns = {"/jg/v/*"})
public class ValidFilter implements Filter {

    private static IUserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        Account account = (Account)request.getSession().getAttribute("account");

        if(account != null) {
            chain.doFilter(req, res);
        } else {
            res.getWriter().write(RespEnum.NOT_LOGIN.toString());
            return;
        }
    }

    @Override
    public void destroy() {

    }
}