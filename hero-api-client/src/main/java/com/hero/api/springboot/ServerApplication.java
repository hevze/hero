package com.hero.api.springboot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * spring-boot启动
 */
@ImportResource(locations={"classpath:spring/spring-context.xml"})
@SpringBootApplication
public class ServerApplication {
    protected static final Logger log = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        /** 在application.properties中配置spring.devtools.restart.enabled=false，此时restart类加载器还会初始化，但不会监视文件更新。
         在SprintApplication.run之前调用System.setProperty(“spring.devtools.restart.enabled”, “false”);可以完全关闭重启支持
         */
        System.setProperty("spring.devtools.restart.enabled", "false");
        try {
            SpringApplication.run(ServerApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
