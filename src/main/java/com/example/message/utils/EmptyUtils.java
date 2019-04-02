package com.example.message.utils;

import java.util.List;

public class EmptyUtils {

    public static Boolean isEmpty(String obj){
        return obj==null||"".equals(obj)||""==obj;
    }

    public static Boolean isEmpty(List obj){
        return obj==null||obj.size()==0;
    }


}