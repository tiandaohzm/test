package com.example.test.module.retry;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @decription 使用注解来标识切点
 * @date 2019/8/22
 * @author 何志铭
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {

    /**
     * 重试次数
     * @return
     */
    int count() default 0;


    /**
     * 超时时间
     * @return
     */
    long time() default 0;


    /**
     * 是否支持异步重试方式
     * @return
     */
    boolean asyn() default false;
}
