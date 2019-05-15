package com.hero.api.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 错误页面拦截器
 */
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/public/error/" + response.getStatus()+".jsp");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}