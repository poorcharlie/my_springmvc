package com.cputest.handler;

import com.cputest.view.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HandlerExecutionChain {

    HandlerMethod handlerMethod;

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public void setHandlerMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }

    public ModelAndView hanle() throws InvocationTargetException, IllegalAccessException {
        Method method = handlerMethod.getMethod();
        Object bean = handlerMethod.getBean();
        String viewName = (String) method.invoke(bean, null);

        return new ModelAndView(viewName);

    }
}
