package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

public class HomeServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Init HomeServlet");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Handle request " + Thread.currentThread().getName());
        PrintWriter writer = resp.getWriter();
        writer.println("Current time: " + LocalTime.now());
    }

    @Override
    public void destroy() {
        System.out.println("Destroy HomeServlet");
    }
}
