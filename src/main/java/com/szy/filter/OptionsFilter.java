package com.szy.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "OptionsFilter", urlPatterns = { "/jg/*" } )
public class OptionsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest http = (HttpServletRequest)req;

		if("OPTIONS".equals(http.getMethod())){
			HttpServletResponse response = (HttpServletResponse)res;
			response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setStatus(200);
		}else{
			chain.doFilter(req, res);
		}
	}

	@Override
	public void destroy() {
		
	}
}
