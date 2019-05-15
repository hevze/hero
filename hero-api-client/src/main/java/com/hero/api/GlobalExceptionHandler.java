package com.hero.api;


import com.alibaba.dubbo.rpc.RpcException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;

/**
 * 全局用户权限输出管理
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public BaseRespose<String> userPermissionException(HttpServletRequest req, Exception e) {
        GlobalException globalException = (GlobalException) e;
        return new BaseRespose<>(globalException.getCode(), globalException.getMsg());
    }

    @ExceptionHandler(RpcException.class)
    @ResponseBody
    public BaseRespose<String> dubboRpcException(HttpServletRequest req, Exception e) {
        RpcException rpcException = (RpcException) e;
        return new BaseRespose<>(rpcException.getCode(), "连接失败,加载中..");
    }

    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseBody
    public BaseRespose<String> socketTimeoutException(HttpServletRequest req, Exception e) {
        SocketTimeoutException socketTimeoutException = (SocketTimeoutException) e;
        return new BaseRespose<>(socketTimeoutException.getMessage(), "连接失败,加载中..");
    }
}