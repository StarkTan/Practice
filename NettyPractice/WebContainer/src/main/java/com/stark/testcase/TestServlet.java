package com.stark.testcase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Stark on 2018/4/12.
 * 自定义的Servlet
 */
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>test</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("GET method");
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        response.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>test</TITLE></HEAD>");
        out.println("<BODY>");
        out.println("POST method");
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        response.setStatus(200);
    }
}
