package com.szy.filter;

import com.szy.RespEnum;
import com.szy.session.LocalUtil;
import com.szy.session.Session;
import com.szy.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by shizhouyong on 2017/1/7.
 */

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/jg/v/*"})
public class LoginFilter implements Filter {

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
            String verify = ss.substring(32);
            if (session == null || !verify.equals(session.getVerify())) {
                res.getWriter().write(RespEnum.NOT_LOGIN.toString());
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