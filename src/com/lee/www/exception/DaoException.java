package com.lee.www.exception;

/**
 * @author ming
 * @date 2023-01-02 21:27
 * @description 用于封装和抛出dao层的异常
 */
public class DaoException extends RuntimeException{

    public DaoException(){
        super();
    }

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message,Throwable cause){
        super(message,cause);
    }

    public DaoException(Throwable cause){
        super(cause);
    }

    protected DaoException(String message,Throwable cause,boolean enableSuppression,boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }

}
