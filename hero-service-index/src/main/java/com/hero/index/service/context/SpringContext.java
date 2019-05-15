package com.hero.index.service.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 此类可以取得Spring的上下文.
 * Spring 使new方法创建的对象可以引用spring管理的bean.
 */
@Lazy(value=false)
@Component
public class SpringContext implements ApplicationContextAware {

    protected static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
