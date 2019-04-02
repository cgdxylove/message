package com.example.message.utils;

/**
 * @author cg
 * @create 2019-03-20 16:20
 */
public class StringUtils {

    public static Boolean isBlank(String msg){
        return msg==null?true:"".equals(msg)?true:false;
    }

}