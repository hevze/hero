package com.hero.api.springboot.lifecircle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartingEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartedEvent) {
        logger.warn("SpringBoot开始加载");
    }
}
