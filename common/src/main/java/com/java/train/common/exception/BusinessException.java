package com.java.train.common.exception;

/**
 * @author Tsk
 * @date 2024/5/14 0014
 */
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum exceptionEnum;


    public  BusinessException(BusinessExceptionEnum e){
       this.exceptionEnum = e;
    }


    public BusinessExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    @Override
    public Throwable fillInStackTrace() {
        return  this;
    }
}
