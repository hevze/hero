package com.hero.api.annotation;

import java.lang.annotation.*;

/**
 * CheckSign 检测参数签名
 * 优先级大于CheckLogin
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface CheckSign {
    boolean sign() default true;
}
