package com.example.message.utils;

import lombok.extern.log4j.Log4j;

import java.lang.reflect.Field;

/**
 * @author cg
 * @create 2019-04-11 15:55
 */
@Log4j
public class HnConstants {
    public static final String split1 = "=";
    //目标报文，每栏次的列属性相同
    public static final String LISTTYPE1 = "listType1";
    //目标报文，栏次的列属性不相同，需要根据list中的列字段属性名称
    public static final String LISTTYPE2 = "listType2";

    public static final String T1_10105_001 = "{\"lc\":\"ewbhxh\",\"bqs\":\"bqshwjlw\",\"bqsFw\":\"bqsfwbdchwxzc\",\"bnlj\":\"bnljhwjlw\",\"bnljFw\":\"bnljfwbdchwxzc\"}";

    public static String getHnConstant(String s){
        try {
            Field field = HnConstants.class.getDeclaredField(s);
            String value=(String) field.get(null);
            return  value;
        } catch (NoSuchFieldException e) {
            log.error("NoSuchFieldException",e);
        }catch (IllegalAccessException e) {
            log.error("IllegalAccessException",e);
        }
        return null ;
    }

}