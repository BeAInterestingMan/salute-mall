package com.salute.mall.common.core.exception;
/**
 *  @Description 自定义业务异常
 *  @author liuhu
 *  @Date 2022/11/23 14:46
 */
public class BusinessException extends RuntimeException{

    private String code;
    private String msg;

    protected BusinessException() {
    }

    protected BusinessException(String code, String msg) {
        super(code + "=" + msg);
        this.code = code;
        this.msg = msg;
    }

    protected BusinessException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
