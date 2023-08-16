package com.example.tomcat_demo.listener;

import javax.servlet.*;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

// 三类型的监听器
// 每一类都有一些细分, 比如监听 request 属性。   request listen 然后靠代码提示就能看到
public class MyServletListener implements
        ServletRequestListener ,
        HttpSessionListener,
        ServletContextListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("触发了新请求");
        System.out.println(sre.getServletRequest().getServerName());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("请求结束");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("新用户登录");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("用户离开");
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
