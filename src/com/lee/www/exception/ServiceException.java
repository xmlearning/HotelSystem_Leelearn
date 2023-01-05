package com.lee.www.exception;

/**
 * @author ming
 * @date 2023-01-02 21:36
 * @description service层的异常类
 */
public class ServiceException extends RuntimeException {
    public ServiceException(){
        super();
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message,Throwable cause){
        super(message,cause);
    }
    protected ServiceException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }
}
