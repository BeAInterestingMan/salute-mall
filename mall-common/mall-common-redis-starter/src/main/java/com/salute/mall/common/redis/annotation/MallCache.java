package com.salute.mall.common.redis.annotation;

import java.lang.annotation.*;

/**
 *  @Description 自定义注解用于实现缓存
 *  @author liuhu
 *  @Date 2022/5/14 23:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MallCache {

    /**
     * 缓存业务key 不传则默认方法名字
     */
    String businessKey() default "";

    /**
     * 过期时间 分钟
     */
    long expireTime() default 0L;

    /**
     * 缓存对象class  当缓存为List时必传
     */
    Class clazz() default Object.class;
}
