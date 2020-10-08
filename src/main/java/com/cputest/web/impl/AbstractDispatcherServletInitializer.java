package com.cputest.web.impl;

import com.cputest.servlet.DispatcherServlet;
import com.cputest.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AbstractDispatcherServletInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcherServlet", new DispatcherServlet());
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }
}
