package com.salute.mall.common.redis.aspect;

import com.alibaba.fastjson.JSON;
import com.salute.mall.common.redis.annotation.MallCache;
import com.salute.mall.common.redis.helper.RedisHelper;
import com.salute.mall.common.redis.utils.Md5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *  @Description aop实现统一缓存
 *  @author liuhu
 *  @Date 2022/5/14 23:50
 */
@Component
@Aspect
@Slf4j
public class CacheAspect {

    @Autowired
    private RedisHelper redisHelper;

    @Pointcut("@annotation(com.salute.mall.common.redis.annotation.MallCache)")
    public void point(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Object[] args = joinPoint.getArgs();
        String paramMD5Str= Md5Utils.getMD5(JSON.toJSONString(args).getBytes(StandardCharsets.UTF_8));
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> returnType = method.getReturnType();

        MallCache annotation = method.getAnnotation(MallCache.class);
        String businessKey = StringUtils.isBlank(annotation.businessKey())?annotation.businessKey():method.getName();
        long time = annotation.expireTime();
        Class clazz = annotation.clazz();

        String key = businessKey +"::"+ paramMD5Str;
        Object cacheValue = redisHelper.get(key);
        String cacheValueStr = Objects.isNull(cacheValue)?StringUtils.EMPTY:(String) cacheValue;
        // 无缓存直接执行原有数据库查询逻辑
        if(StringUtils.isBlank(cacheValueStr)){
            result = joinPoint.proceed();
            redisHelper.set(key,JSON.toJSONString(result),time, TimeUnit.MINUTES);
        }
        result = deserialize(cacheValueStr, returnType, clazz);
        log.info("方法:{}进入缓存,key:{}",method.getName(),key);
        return result;
    }

    /**
     * @Description 序列化 如果是数组 需要指定List<T>泛型  不然序列化报错
     * @author liuhu
     * @param json
     * @param clazz
     * @param modelType
     * @date 2022/5/15 0:38
     * @return java.lang.Object
     */
    private Object deserialize(String json, Class clazz, Class modelType) {
        return clazz.isAssignableFrom(List.class) ? JSON.parseArray(json, modelType) : JSON.parseObject(json, clazz);
    }
}
