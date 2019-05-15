package com.hero.api.springboot.lifecircle;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.hero.api.interceptors.ApiInterceptor;
import com.hero.api.interceptors.ErrorPageInterceptor;
import com.hero.api.interceptors.RpcInterceptor;
import com.hero.api.interceptors.WebRpcInterceptor;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * springboot提供拦截器
 *
 * @Configuration 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
 */
@Configuration
public class ApiWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorPageInterceptor());//.addPathPatterns("/action/**", "/mine/**");默认所有
        registry.addInterceptor(new RpcInterceptor()).addPathPatterns("/api/**");
        //对来自/** 这个链接来的请求进行拦截
        registry.addInterceptor(new ApiInterceptor()).addPathPatterns("/api/**");
        // web
        registry.addInterceptor(new WebRpcInterceptor()).addPathPatterns("/web/api/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //不配置这，会出现ERR_TOO_MANY_REDIRECTS重定向的次数过多
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        /**
         * 1.需要定义一个convert转换消息的对象
         * 2.创建配置信息，加入配置信息：比如是否需要格式化返回的json
         * 3.converter中添加配置信息
         * 4.convert添加到converters当中
         */
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(fastJsonHttpMessageConverter);
    }

    /**
     * 文件上传临时路径
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        String path = System.getProperty("user.dir") + "/upload/tmp";
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        factory.setLocation(path);
        return factory.createMultipartConfig();
    }
}
