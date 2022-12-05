package com.salute.mall.common.core.entity;

import java.io.Serializable;
/**
 *  @Description 通用返回
 *  @author liuhu
 *  @Date 2022/11/23 13:38
 */
public class Result<T> implements Serializable {

    private boolean status = false;

    private String message;

    private T result;

    private String code;

    private boolean success = true;

    public static <T> Result<T> error(String statusCode, String message) {
        return new Result(message, (Object)null, statusCode);
    }

    public static <T> Result<T> success(T data) {
        return new Result(true, true,"操作成功", data, "200");
    }

    public static <T> Result<T> success() {
        return new Result(true,true, "操作成功", (Object)null, "200");
    }

    public Result() {
    }

    public Result(String message, T result, String statusCode) {
        this.message = message;
        this.result = result;
        this.code = statusCode;
    }

    public Result(boolean status,boolean success, String message, T result, String statusCode) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.result = result;
        this.code = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
