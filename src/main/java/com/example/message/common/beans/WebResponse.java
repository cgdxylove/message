package com.example.message.common.beans;

/**
 * @author cg
 * @create 2019-03-25 13:56
 */
public class WebResponse {
    private String code ;
    private String msg ;
    private String status ;
    private Object data ;

    public WebResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void error(String code,String msg){
        this.code = code ;
        this.msg = msg ;
        this.status = "500" ;
    }

    public void success(String code,String msg){
        this.success(code,msg,null);
    }

    public void success(String code,String msg,Object data){
        this.code = code ;
        this.msg = msg ;
        this.data = data ;
        this.status = "200" ;
    }


}