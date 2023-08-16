package com.example.tomcat_demo.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class VisitTimeFilter implements Filter {
    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 第一次调用的时候初始化
        this.filterConfig = filterConfig;
        System.out.println("HelloFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean tooManyVisit = false;
        if (request instanceof HttpServletRequest) {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null) {
                Map<String, Cookie> collect = Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, x -> x));
                Cookie visit_time = collect.get("visit_time");
                if (visit_time != null) {
                    // 假设就是 2 秒钟只能访问一次
                    tooManyVisit = true;
                }
            }
        }
        if (tooManyVisit) {
            System.out.println("访问太频繁了");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("HelloFilter destroy");
    }
}
