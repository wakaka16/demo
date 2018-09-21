package com.zhifei.demo.common.bean;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}
