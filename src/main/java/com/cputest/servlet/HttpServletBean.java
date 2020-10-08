package com.cputest.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletBean extends HttpServlet {

    @Override
    public void init() throws ServletException {
        initServletBean();
    }

    protected void initServletBean() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doService(req, resp);
        } catch (Exception ex) {

        }

    }

    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }
}
