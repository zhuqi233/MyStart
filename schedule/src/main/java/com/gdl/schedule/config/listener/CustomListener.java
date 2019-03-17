package com.gdl.schedule.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 实现ServletContextListener，覆盖其两个方法
 * @author Administrator
 *
 */
@WebListener
public class CustomListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("servletContext初始化......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("servletContext销毁......");
    }
}
