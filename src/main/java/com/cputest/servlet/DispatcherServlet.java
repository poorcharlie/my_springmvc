package com.cputest.servlet;

import com.cputest.handler.HandlerExecutionChain;
import com.cputest.handler.HandlerMethod;
import com.cputest.view.ModelAndView;
import com.cputest.web.RequestMappingHandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends FrameworkServlet {

    private RequestMappingHandlerMapping handlerMapping;

    public DispatcherServlet() {
        this.handlerMapping = new RequestMappingHandlerMapping();
    }

    @Override
    protected void onRefresh() {
        initStrategies();
    }

    private void initStrategies() {
        System.out.println("DispatcherServlet 容器开始初始化");

        handlerMapping.initHandlerMappings();
    }

    @Override
    protected void doService(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        doDispatch(req, resp);
    }

    protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String uri = req.getRequestURI();
        HandlerExecutionChain handler = getHandler(uri);
        if (handler == null) {
            noHandlerFound(req, resp);
            return;
        }

        ModelAndView view = handler.hanle();

        render(view, req, resp);

    }

    private void render(ModelAndView view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewName = view.getViewName();
        req.getRequestDispatcher("/WEB-INF/view/" + viewName + ".jsp")
                .forward(req, resp);
//        resp.sendRedirect("/WEB-INF/view/" + viewName + ".jsp");
    }

    private HandlerExecutionChain getHandler(String url) {
        HandlerMethod handlerMethod = handlerMapping.getHandlerMethod(url);
        if (handlerMethod == null) {
            return  null;
        }

        return new HandlerExecutionChain(handlerMethod);
    }

    protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception {

            throw new Exception("404 not found");
    }
}
