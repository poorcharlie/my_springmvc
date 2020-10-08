package com.cputest.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrameworkServlet extends HttpServletBean {

    @Override
    protected void initServletBean() {
        onRefresh();
    }

    protected void onRefresh() {
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    }


}
