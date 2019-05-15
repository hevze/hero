package com.hero.api.springboot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.util.WebAppRootListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * 相当于web.xml
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class WebAppRootContext implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext){
        servletContext.addListener(WebAppRootListener.class);
        servletContext.addListener(RequestContextListener.class);
    }

/*

    private String getApiAccessUrl() {
        Properties pro = PropertiesUtils.read("APIAccess.properties");
        String apiaccess = pro.getProperty("APIACCESS");
        return apiaccess;
    }
*/


}