package org.itstep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println("<form method='post' action='/'>" +
                "<label>Login: <input name='login'/></label><br/>" +
                "<label>Password: <input name='password' type='password'/></label><br/>" +
                "<input type='submit'/>" +
                "</form>");
    }
}
