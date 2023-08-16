package com.example.tomcat_demo;

import com.google.gson.Gson;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        // 初次调用会 set_config 和 调用 init
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //testRequest(request);
        //testConfig();
        test_cookie(request, response);


        // Hello
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    void testRequest(HttpServletRequest request) {
        print("/* testRequest */");
        print("/* 获取客户机信息 */");
        print(request.getMethod());       // get,post 等
        print(request.getProtocol());     // 协议+版本
        print(request.getContextPath());  // 请求的 web 程序路径
        // 客户端 ip,port,name
        print(request.getRemoteUser() + "+" + request.getRemoteHost() + "+" + request.getRemoteAddr() + "+" + request.getRemotePort());
        // 服务端 ip,port,name
        print(request.getLocalName() + "+" + request.getLocalAddr() + "+" + request.getLocalPort());
        print(request.getServerName());
        print(request.getScheme());


        print("/* 请求头 */");
        print(request.getHeaderNames());

        print("/* 请求参数 */");
        print(request.getParameterMap());
        print(request.getParameter("name"));
        print(request.getParameterValues("name"));

        print("/* url 相关 */");
        print(request.getRequestURL());     // 完整的 url
        print(request.getRequestURI());     // url 前的东西
        print(request.getContextPath());    // url 主体
        print(request.getServletPath());    // servlet 映射的 path
        print(request.getQueryString());    // 请求参数
    }

    void testConfig() {
        print("/* testConfig */");
        ServletConfig config = getServletConfig();
        print(config.getServletName());
        print(config.getServletContext());
        print(config.getInitParameterNames());
        print(config.getInitParameter("map_key"));
    }

    void test_cookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            cookies = new Cookie[0];
        }
        // 设置了 cookie 第二次访问就可以拿得到.
        // cookie 是 http 协议的一部分
        Map<String, Cookie> cookieMap = Arrays.stream(cookies).collect(Collectors.toMap(Cookie::getName, x -> x));
        print(cookieMap.get("passwd"));
        if (cookieMap.get("passwd") == null) {
            Cookie cookie = new Cookie("passwd", "12345");
            cookie.setMaxAge(30);
            response.addCookie(cookie);
            print("set cookie");
        }
        if (cookieMap.get("visit_time") == null) {
            Cookie visit_time = new Cookie("visit_time", "2");
            visit_time.setMaxAge(2);
            response.addCookie(visit_time);
        }
    }

    void print(Object o) {
        System.out.println(o);
    }


    public void destroy() {
    }
}