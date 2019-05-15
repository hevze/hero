package com.hero.api.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RpcInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String ip = getIpAddress(request);
        request.setAttribute("loginIp", ip);

        long stime = System.currentTimeMillis();
        request.setAttribute("stime", System.currentTimeMillis());
        //跨域api测试
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie,accessKey,Devices-Token,timestamp");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT");

        if (request.getMethod().equals("OPTIONS")) {
            try {
                PrintWriter out=response.getWriter();
                out.print("");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        /*
        String timestamp = request.getHeader("timestamp");
        System.out.println("  -----------------------------------> 请求地址 :" +  request.getRequestURL());
        System.out.println( " ------------------------------------>timestamp:" + timestamp + "   FORMAT:" + DateUtils.formatDate(new Date(Long.parseLong(timestamp)),"yyyyMMdd HH:mm:ss"));
        long requestTime = Long.parseLong(timestamp == null ? "0" : timestamp);
        if (!request.getRequestURI().contains("error") && Math.abs(stime - requestTime) > 20 * 1000) {
            throw new GlobalException(GlobalException.TIMESTAMP_ERROR, "timestamp错误!");
        }*/
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie ,accessKey,Devices-Token,timestamp");
        long stime = (long) httpServletRequest.getAttribute("stime");
        long etime = System.currentTimeMillis();
        System.out.println(httpServletRequest.getRequestURL() + " 接口耗时：------------------" + (etime - stime));
    }



    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
