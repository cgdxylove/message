package com.example.message.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建目标xml主体架构
 * @author cg
 * @create 2019-04-11 10:30
 */
@Log4j
public class JsonTOXml {
    public  static  String  xmlJson ="{\"head\":{\"publicHead\":{\"nsrsbh\":\"\",\"nsrmc\":\"\",\"tbrq\":\"\",\"tbr\":\"\",\"sssq\":{\"rqQ\":\"\",\"rqZ\":\"\"},\"dymm\":\"\"},\"privateHead\":{\"nsr\":{\"bsr\":\"\",\"cwfzr\":\"\",\"fddbr\":\"\",\"lxdh\":\"\"},\"wtdlr\":{\"dlrmc\":\"\",\"jbr\":\"\",\"dlr\":\"\",\"lxdh\":\"\"},\"bdcxs\":{\"sffdckfqy\":\"\",\"sfdqxsbdc\":\"\",\"xsbdckcqxse\":\"\",\"xsbdcbhskce\":\"\"},\"bcsbbz\":\"\"}},\"body\":{\"jexx\":[{\"lc\":\"\",\"bqs\":\"\",\"bqsFw\":\"\",\"bnlj\":\"\",\"bnljFw\":\"\"},{\"lc\":\"\",\"bqs\":\"\",\"bqsFw\":\"\",\"bnlj\":\"\",\"bnljFw\":\"\"}]}}";

    public static Document createDocument(){
        return DocumentHelper.createDocument();
    }



    public  static Element createRootElement(Document document){
        Element taxML = document.addElement("taxML", "http://www.chinatax.gov.cn/dataspec/");
        taxML.addNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        taxML.addAttribute("xsi:schemaLocation","http://www.chinatax.gov.cn/dataspec/ C:\\Users\\Lenovo\\Desktop\\申报\\湖南\\增值税一般纳税人申报表XSD定义\\10104_001.xsd");
        taxML.addAttribute("cnName","增值税一般纳税人申报");
        taxML.addAttribute("name","sbbXgmzzssbb");
        taxML.addAttribute("version","V1.0");
        return taxML;
    }

    public  static Element createSbNameElement(Element taxML){
        String name =  taxML.attributeValue("name");
        Element first = taxML.addElement(name);
        return first;
    }

    public static LinkedHashMap jsonToMap(String  xmlJson){
        //有序map
        LinkedHashMap mapTypes=  JSON.parseObject(xmlJson,LinkedHashMap.class, Feature.OrderedField);
        return mapTypes ;
    }
    // 构建xml主体结构
    private static void addElementToRoot(Map mapTypes, Element taxML) {
        if(mapTypes!=null&&mapTypes.size()>0){
            //map不为空的情况下
            for(Object key :mapTypes.keySet()){
                String elemnetName = key.toString() ;
                Object object = mapTypes.get(key);
                if(object instanceof Map){
                    Element elemnet =  taxML.addElement(elemnetName);
                    addElementToRoot((Map)object,elemnet);
                }else if(object instanceof List){
                    log.info(object);
                    List<Map> list = ((List<Map>) object);
                    for(Map map : list){
                        Element elemnet =  taxML.addElement(elemnetName);
                        addElementToRoot(map,elemnet);
                    }
                }else{
                    Element elemnet =  taxML.addElement(elemnetName);
                }
            }
        }
    }

    //不打印list，只打印list根目录
    public static void addElementToRoot2(Map mapTypes, Element taxML) {
        if(mapTypes!=null&&mapTypes.size()>0){
            for(Object key :mapTypes.keySet()){
                String elemnetName = key.toString() ;
                Object object = mapTypes.get(key);
                Element elemnet =  taxML.addElement(elemnetName);
                if(object instanceof Map){
                    addElementToRoot2((Map)object,elemnet);
                }
            }
        }
    }


    public static Document createXml(){
        Document document = createDocument();
        LinkedHashMap  mapTypes = jsonToMap(xmlJson);
        addElementToRoot(mapTypes,createSbNameElement(createRootElement(document)));
        log.info("==>"+document.asXML());
        return document ;
    }

    public static void main(String[] args) {
        Document document = createDocument();
        LinkedHashMap  mapTypes = jsonToMap(xmlJson);
        addElementToRoot2(mapTypes,createSbNameElement(createRootElement(document)));
        log.info("==>"+document.asXML());
    }
}