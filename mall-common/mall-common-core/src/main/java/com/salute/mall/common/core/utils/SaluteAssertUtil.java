package com.salute.mall.common.core.utils;

import com.salute.mall.common.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

/**
 *  @Description 断言工具
 *  @author liuhu
 *  @Date 2022/11/23 18:30
 */
@Slf4j
public class SaluteAssertUtil {

    public static void isTrue(boolean flag,String message){
        if(!flag){
            throw new BusinessException("500",message);
        }
    }

    public static void isTrue(boolean flag,String code,String message){
        if(!flag){
            throw new BusinessException(code,message);
        }
    }
}
