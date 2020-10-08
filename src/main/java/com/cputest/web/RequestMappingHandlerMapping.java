package com.cputest.web;

import com.cputest.annotation.ComponentScan;
import com.cputest.annotation.Controller;
import com.cputest.annotation.RequestMapping;
import com.cputest.config.SpringMvcConfig;
import com.cputest.handler.HandlerMethod;
import com.cputest.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMappingHandlerMapping {

    private Map<String, HandlerMethod> registry = new HashMap<>();

    public void initHandlerMappings() {
        ComponentScan componentScan = SpringMvcConfig.class.
                getDeclaredAnnotation(ComponentScan.class);

        String value = componentScan.value();

        if (StringUtils.isEmpty(value)) {
            return;
        }

        List<Class<?>> classes = ClassUtil.getClasses(value);

        for (Class<?> clazz : classes) {
            Controller controller = clazz.getDeclaredAnnotation(Controller.class);
            if (controller == null) {
                continue;
            }

            Method[] declaredMethods = clazz.getDeclaredMethods();

            for (Method method : declaredMethods) {
                RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);

                if (requestMapping == null) {
                    continue;
                }

                String url = requestMapping.value();

                registry.put(url, new HandlerMethod(newInstance(clazz), method));

            }

        }



    }


    private Object newInstance(Class clazz) {
        Object o = null;
        try {
             o = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;

    }

    public HandlerMethod getHandlerMethod(String url) {
        return registry.get(url);
    }
}
