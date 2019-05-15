package com.hero.api.init;

import com.hero.api.annotation.CheckLogin;
import com.hero.api.annotation.CheckSign;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class InitProvider implements BeanPostProcessor {
    /**
     * 检测是否登录
     */
    public static Map<String, Boolean> mCheckLoginMap = new HashMap<>();
    /**
     * 检测参数加签
     */
    public static Map<String, Boolean> mCheckSignMap = new HashMap<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 获取方法上的注解
        Method[] methods = bean.getClass().getDeclaredMethods();
        boolean isController = bean.getClass().isAnnotationPresent(RestController.class);
        if (isController) {
            if (bean.getClass().isAnnotationPresent(RequestMapping.class)) {
                RequestMapping restMapping = bean.getClass().getAnnotation(RequestMapping.class);
                String urlPrix = "/";
                if (restMapping.value().length != 0) {
                    urlPrix = urlPrix + restMapping.value()[0];
                }
                for (Method method : methods) {
                    if (method.isAnnotationPresent(CheckLogin.class)) {
                        CheckLogin checkLogin = method.getAnnotation(CheckLogin.class);
                        if (checkLogin.isCheck()) {
                            if (!method.isAnnotationPresent(RequestMapping.class)) {
                                throw new IllegalStateException("CheckLogin注解必须含有RequestMapping");
                            }
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String[] urls = requestMapping.value();
                            for (String url : urls) {
                                mCheckLoginMap.put(urlPrix + url, true);
                            }
                        }
                    }
                    //
                    if (method.isAnnotationPresent(CheckSign.class)) {
                        CheckSign checkSign = method.getAnnotation(CheckSign.class);
                        if (checkSign.sign()) {
                            if (!method.isAnnotationPresent(RequestMapping.class)) {
                                throw new IllegalStateException("CheckSign注解必须含有RequestMapping");
                            }
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String[] urls = requestMapping.value();
                            for (String url : urls) {
                                mCheckSignMap.put(urlPrix + url, true);
                            }
                        }
                    }
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
