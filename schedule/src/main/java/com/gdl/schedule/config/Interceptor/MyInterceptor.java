package com.gdl.schedule.config.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //controller方法调用之前
        System.err.println(MyInterceptor.class.getName()+" : 在请求之前调用");
        //进行逻辑判断，如果ok就返回true，不行就返回false，返回false就不会处理改请求
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后
        System.err.println(MyInterceptor.class.getName()+" :请求处理之后视图渲染之前");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作
        System.err.println(MyInterceptor.class.getName()+" :视图渲染之后");
    }

}
