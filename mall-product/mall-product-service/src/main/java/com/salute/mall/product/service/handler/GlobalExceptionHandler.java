package com.salute.mall.product.service.handler;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  @Description 全局异常处理
 *  @author liuhu
 *  @Date 2022/11/23 18:17
 */
@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler(value = BusinessException.class)
    public Result<Void> handlerBusinessException(BusinessException e){
      log.info("execute handlerBusinessException info",e);
      return Result.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<Void> handlerException(Exception e){
        log.error("execute handlerException info",e);
        return Result.error("500","系统异常，请联系管理员");
    }
}
