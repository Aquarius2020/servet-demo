package com.example.tomcat_demo;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        testRequest(request);


        // Hello
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    void testRequest(HttpServletRequest request) {

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


    void print(Object o) {
        System.out.println(o);
    }


    public void destroy() {
    }
}