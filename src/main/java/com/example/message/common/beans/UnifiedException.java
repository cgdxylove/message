package com.example.message.common.beans;

/**
 * @author cg
 * @create 2019-03-25 14:33
 */
public class UnifiedException  extends RuntimeException{
    private String code;
    private String message;
    private String exceptionStackInfo = "";

    public UnifiedException() {
    }


    public UnifiedException(String code) {
        this.code = code;
    }

    public UnifiedException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public UnifiedException(String code, String message, String exceptionStackInfo) {
        this.code = code;
        this.message = message;
        this.exceptionStackInfo = exceptionStackInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExceptionStackInfo() {
        return exceptionStackInfo;
    }

    public void setExceptionStackInfo(String exceptionStackInfo) {
        this.exceptionStackInfo = exceptionStackInfo;
    }
}