package com.hero.api.springboot.lifecircle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class BootImplDisposableBean implements DisposableBean, ExitCodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(BootImplDisposableBean.class);

    @Override
    public void destroy() throws Exception {
        logger.info("---------------------------------");
        logger.info("-----------hero boot 容器销毁-----------");
        logger.info("---------------------------------");
    }

    @Override
    public int getExitCode() {
        return 5;
    }
}
