package com.cputest.web;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Method;
import java.util.Set;

@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {


    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for (Class<?> clazz : set) {
            try {
                Method onStartup = clazz.getDeclaredMethod("onStartup", ServletContext.class);
                Object o = clazz.newInstance();
                onStartup.invoke(o, servletContext);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }
}
