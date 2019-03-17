package com.gdl.schedule.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName="customFilter",urlPatterns="/*")
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("CustomFilter初始化......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println("CustomFilter指定过滤器操作......");
        //执行操作后必须doFilter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.err.println("CustomFilter过滤器销毁");
    }
}
