package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.Enumeration;

public class HomeServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init HomeServlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Handle request " + Thread.currentThread().getName());

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        resp.addHeader("myHeaderName", "myHeaderValue");

        String method = req.getMethod();
        String requestURI = req.getRequestURI();
        Cookie[] cookies = req.getCookies();
        StringBuilder stringBuilder = new StringBuilder();
        appendMainInfo(method, requestURI, stringBuilder);
        appendAllHeaders(req, stringBuilder);
        appendAllParameters(req, stringBuilder);
        appendCookies(req, cookies, stringBuilder);
        stringBuilder.append("<p>Current time: " + LocalTime.now() + "</p>");
        PrintWriter writer = resp.getWriter();
        writer.println(stringBuilder.toString());
    }

    private void appendMainInfo(String method, String requestURI, StringBuilder stringBuilder) {
        stringBuilder.append("<h1>Hello Servlet</h1>");
        stringBuilder.append("<p>").append("Method: ").append(method).append("</p>");
        stringBuilder.append("<p>").append("requestURI: ").append(requestURI).append("</p>");
    }

    private void appendCookies(HttpServletRequest req, Cookie[] cookies, StringBuilder stringBuilder) {
        if(cookies == null) return;
        stringBuilder.append("<h2>Cookies</h1>");
        for(Cookie cookie: cookies) {
            stringBuilder
                    .append("<li>")
                    .append("<strong>")
                    .append(cookie.getName())
                    .append("</strong>")
                    .append(": ")
                    .append(cookie.getValue())
                    .append("</li>");
        }
    }

    private void appendAllParameters(HttpServletRequest req, StringBuilder stringBuilder) {
        stringBuilder.append("<h2>Parameters</h1>");
        Enumeration<String> parameterNames = req.getParameterNames();
        stringBuilder.append("<ol>");
        while (parameterNames.hasMoreElements()) {
            String parameter = parameterNames.nextElement();
            stringBuilder
                    .append("<li>")
                    .append("<strong>")
                    .append(parameter)
                    .append("</strong>")
                    .append(": ")
                    .append(req.getParameter(parameter))
                    .append("</li>");
        }
        stringBuilder.append("</ol>");
    }

    private void appendAllHeaders(HttpServletRequest req, StringBuilder stringBuilder) {
        Enumeration<String> headerNames = req.getHeaderNames();
        stringBuilder.append("<h2>Headers</h2>");
        stringBuilder.append("<ol>");
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            stringBuilder
                    .append("<li>")
                    .append("<strong>")
                    .append(header)
                    .append("</strong>")
                    .append(": ")
                    .append(req.getHeader(header))
                    .append("</li>");
        }
        stringBuilder.append("</ol>");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy HomeServlet");
    }
}
