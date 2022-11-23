package com.salute.mall.product.service.handler;

import com.salute.mall.common.core.entity.Result;
import com.salute.mall.common.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 *  @Description 全局异常处理
 *  @author liuhu
 *  @Date 2022/11/23 18:17
 */
@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler{

    /**
     * @Description 业务异常拦截
     * @author liuhu
     * @param e
     * @date 2022/11/23 22:03
     * @return com.salute.mall.common.core.entity.Result<java.lang.Void>
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result<Void> handlerBusinessException(BusinessException e){
      log.info("execute handlerBusinessException info",e);
      return Result.error(e.getCode(), e.getMsg());
    }

    /**
     * @Description 对象valid拦截
     * @author liuhu
     * @param e
     * @date 2022/11/23 22:03
     * @return com.salute.mall.common.core.entity.Result<java.lang.Void>
     */
    @ExceptionHandler(value = BindException.class)
    public Result<Void> handleBindException(BindException e) {
        log.info("execute handleBindException info",e);
        String msg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(n -> String.format("%s: %s", n.getField(), n.getDefaultMessage()))
                .reduce((x, y) -> String.format("%s; %s", x, y))
                .orElse("参数输入有误");
        return Result.error("500",msg);
    }

    /**
     * @Description 单个参数 @NotBlank拦截
     * @author liuhu
     * @param e
     * @date 2022/11/23 22:02
     * @return com.salute.mall.common.core.entity.Result<java.lang.Void>
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<Void> handleConstraintViolationException(ConstraintViolationException e) {
        log.info("execute handleConstraintViolationException info",e);
        String msg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        return Result.error("500",msg);
    }

    /**
     * @Description 系统异常拦截
     * @author liuhu
     * @param e
     * @date 2022/11/23 22:02
     * @return com.salute.mall.common.core.entity.Result<java.lang.Void>
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Void> handlerException(Exception e){
        log.error("execute handlerException info",e);
        return Result.error("500","系统异常，请联系管理员");
    }
}
