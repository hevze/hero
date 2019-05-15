package com.hero.index.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @描述: 启动Dubbo服务用的MainClass.
 * @作者: WuShuicheng .
 * @创建时间: 2013-11-5,下午9:47:55 .
 * @版本: 1.0 .
 */
public class DubboProvider_Index {
	private static final Log log = LogFactory.getLog(DubboProvider_Index.class);
	
    public static void main(String[] args) {
    	
        try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
            log.error("  首页启动成功 。。。 。。 。。 。 ");
        } catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}
        
        synchronized (DubboProvider_Index.class) {
            while (true) {
                try {
                    DubboProvider_Index.class.wait();
                } catch (InterruptedException e) {
                	log.error("== synchronized error:",e);
                }
            }
        }
    }
    
}