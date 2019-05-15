package com.hero.api.springboot.lifecircle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        logger.warn("SpringBoot加载完毕");
    }
}
