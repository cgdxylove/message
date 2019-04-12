package com.example.message.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 获取报表数据，进行最终报表转换
 * @author cg
 * @create 2019-04-11 13:06
 */
@Log4j
public class MapToXml {

    public static void getMapFromBZBW(String bzbw) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(bzbw);
            //获得某个节点的属性对象
            Element root = doc.getRootElement();
            //获取表头节点
            Element sbbinfo = root.element("sbbinfo");
            //获取标准报文数据
            Element jsxgs_whsyjsf_ssb = root.element("jsxgs_whsyjsf_ssb");//主表节点
            //获取附表节点
            Element fbElement = root.element("jsxgs_whsyjsf_jcxmqd");//附表节点
            //处理附表节点
            String fbStr = fbElement.asXML();
        } catch (DocumentException e) {

        }
    }

    //根据关系字符串获取节点
    public static Element getElementFromDocument(Document document ,String inner_relationship){
        inner_relationship = inner_relationship.replace("\n","").replaceAll("\t","").replace(" ","");
        String[] jdgx = inner_relationship.split(HnConstants.split1);
        log.info(jdgx);
        Element element = document.getRootElement();
        log.info("RootElement-->"+element.getName());
        if(jdgx.length>0){
            for(int i=0;i<jdgx.length;i++){

                if(i==jdgx.length-1){
                    element = element.element(jdgx[i]);
                    return element ;
                }
                log.info("将要获取element的名称"+"--->"+jdgx[i]);
                element = element.element(jdgx[i]);
            }
        }
        return null ;
    }

    //根据字符串创建节点，并且塞值
    public static Element createElementFromDocument(Document document ,String inner_relationship){
        inner_relationship = inner_relationship.replace("\n","").replaceAll("\t","").replace(" ","");
        String[] jdgx = inner_relationship.split(HnConstants.split1);
        Element element = document.getRootElement();
        log.info("RootElement-->"+element.getName());
        if(jdgx.length>0){
            for(int i=0;i<jdgx.length;i++){
                //element.addElement();
            }
        }
        return null ;
    }


    public static String bzbw = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><jsxgs_zzs_xgmxxVO><sbbinfo><sbzlbh>10102</sbzlbh><ssqq>2019-02-01</ssqq><ssqz>2019-02-28</ssqz><nsrsbh>91320115MA1T92HY6F</nsrsbh><area>32</area><nsqxdm>2</nsqxdm></sbbinfo><jsxgs_zzs_xgm_sbb><sbbGridlbVO><ewbhxh>1</ewbhxh><bqshwjlw>0</bqshwjlw><bqsfwbdchwxzc>92560.53</bqsfwbdchwxzc><bnljhwjlw>0</bnljhwjlw><bnljfwbdchwxzc>322611.94</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>2</ewbhxh><bnljfwbdchwxzc>155326.03</bnljfwbdchwxzc><bqshwjlw>0</bqshwjlw><bqsfwbdchwxzc>9786.41</bqsfwbdchwxzc><bnljhwjlw>0</bnljhwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>3</ewbhxh><bnljfwbdchwxzc>167285.91</bnljfwbdchwxzc><bqsfwbdchwxzc>82774.12</bqsfwbdchwxzc><bnljhwjlw>0</bnljhwjlw><bqshwjlw>0</bqshwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>4</ewbhxh><bnljfwbdchwxzc>0</bnljfwbdchwxzc><bqsfwbdchwxzc>0</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>5</ewbhxh><bnljfwbdchwxzc>0</bnljfwbdchwxzc><bqsfwbdchwxzc>0</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>6</ewbhxh><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljfwbdchwxzc>0</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>7</ewbhxh><bqshwjlw>0</bqshwjlw><bnljhwjlw>0</bnljhwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>8</ewbhxh><bqshwjlw>0</bqshwjlw><bnljhwjlw>0</bnljhwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>9</ewbhxh><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljhwjlw>0</bnljhwjlw><bnljfwbdchwxzc>96796.96</bnljfwbdchwxzc><bqshwjlw>0</bqshwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>10</ewbhxh><bqshwjlw>0</bqshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljhwjlw>0</bnljhwjlw><bnljfwbdchwxzc>96796.96</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>11</ewbhxh><bnljfwbdchwxzc>0</bnljfwbdchwxzc><bqshwjlw>0</bqshwjlw><bnljhwjlw>0</bnljhwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>12</ewbhxh><bnljhwjlw>0</bnljhwjlw><bnljfwbdchwxzc>0</bnljfwbdchwxzc><bqshwjlw>0</bqshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>13</ewbhxh><bnljfwbdchwxzc>0</bnljfwbdchwxzc><bqshwjlw>0</bqshwjlw><bnljhwjlw>0</bnljhwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>14</ewbhxh><bnljhwjlw>0</bnljhwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljfwbdchwxzc>0</bnljfwbdchwxzc><bqshwjlw>0</bqshwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>15</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bnljfwbdchwxzc>9678.4</bnljfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw><bqsfwbdchwxzc>2776.87</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>16</ewbhxh><bnljfwbdchwxzc>480</bnljfwbdchwxzc><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw><bqsyshwjlw>0</bqsyshwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>17</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw><bnljfwbdchwxzc>3056.29</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>18</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljfwbdchwxzc>3056.29</bnljfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>19</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw><bnljfwbdchwxzc>0</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>20</ewbhxh><bqsfwbdchwxzc>2776.87</bqsfwbdchwxzc><bqsyshwjlw>0</bqsyshwjlw><bnljyshwjlw>0</bnljyshwjlw><bnljfwbdchwxzc>9198.4</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>21</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>293.59</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>22</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>2483.28</bqsfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>23</ewbhxh><bnljyshwjlw>0</bnljyshwjlw><bqsyshwjlw>0</bqsyshwjlw></sbbGridlbVO><sbbGridlbVO><ewbhxh>24</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw><bnljfwbdchwxzc>0</bnljfwbdchwxzc></sbbGridlbVO><sbbGridlbVO><ewbhxh>25</ewbhxh><bqsyshwjlw>0</bqsyshwjlw><bqsfwbdchwxzc>0</bqsfwbdchwxzc><bnljyshwjlw>0</bnljyshwjlw><bnljfwbdchwxzc>0</bnljfwbdchwxzc></sbbGridlbVO></jsxgs_zzs_xgm_sbb><jsxgs_zzs_xgm_flzl/><jsxgs_zzs_xgm_flzl4/><jsxgs_zzs_xgm_jmsmxb/><jsxgs_zzs_xgm_jcxmqd/></jsxgs_zzs_xgmxxVO>";

    public static String relationship = "{\"sbbXgmzzssbb=head=publicHead=nsrsbh\": \"sbbinfo=nsrsbh\",\"sbbXgmzzssbb=head=publicHead=sssq=rqQ\": \"sbbinfo=ssqq\",\"sbbXgmzzssbb=head=publicHead=sssq=rqZ\": \"sbbinfo=ssqz\",\"sbbXgmzzssbb=body=jexx\":\"jsxgs_zzs_xgm_sbb=sbbGridlbVO##listType1%T1_10105_001\"}";

    public static void main(String[] args) throws DocumentException {
       /* LinkedHashMap mapTypes=  JSON.parseObject(relationship,LinkedHashMap.class, Feature.OrderedField);
        System.out.println(mapTypes);*/
        Document document = JsonTOXml.createDocument();
        LinkedHashMap mapTypes = JsonTOXml.jsonToMap(JsonTOXml.xmlJson);
        JsonTOXml.addElementToRoot2(mapTypes,JsonTOXml.createSbNameElement(JsonTOXml.createRootElement(document)));
        //String inner_relationship = "sbbXgmzzssbb=head=publicHead=nsrsbh";
        LinkedHashMap relationMap = JsonTOXml.jsonToMap(relationship);
        for(Object object : relationMap.keySet()){
            String inner_relationship_MB = object.toString();
            String inner_relationship_BZ = relationMap.get(object).toString();
            if(inner_relationship_BZ.contains("##")){
                String[] relation_inners_BZ =  inner_relationship_BZ.split("##");
                log.info(JSON.toJSON(relation_inners_BZ));
                if(relation_inners_BZ.length>0){
                    String  inner0 =   relation_inners_BZ[0];
                    String  inner1 =   relation_inners_BZ[1];
                        //特殊情况1：
                        if(inner1.contains(HnConstants.LISTTYPE1)){
                            //获取标准报文节点
                            String listRoot_BZ = inner0.replace("\n","").replaceAll("\t","").replace(" ","");
                            String[] jdgx_list_BZ = listRoot_BZ.split(HnConstants.split1);
                            String nodePosition = "" ;
                            if(jdgx_list_BZ.length>0){
                                for(int i=0;i<jdgx_list_BZ.length;i++){
                                    nodePosition =nodePosition +"//"+ jdgx_list_BZ[i] ;
                                }
                            }
                            log.info(nodePosition);
                            Document doc  = DocumentHelper.parseText(bzbw);
                            Element element = doc.getRootElement();
                            log.info("RootElement-->"+element.getName());
                            List<Element> list = element.selectNodes(nodePosition);
                            //根据成员关系 relation_listMembers ，增加节点并且新增
                            String[] inners_BZ = inner1.split("%");
                            String mark = inners_BZ[1];
                            String relation_inners=  HnConstants.getHnConstant(mark);
                            LinkedHashMap relation_inners_map=  JSON.parseObject(relation_inners,LinkedHashMap.class, Feature.OrderedField);

                            for (Element e: list){
                                Element element_MB =  getElementFromDocument(document,inner_relationship_MB);
                                if(relation_inners_map!=null&&relation_inners_map.size()>0){
                                    for (Object key : relation_inners_map.keySet()){
                                        String key_MB_jdmc =  key.toString();
                                        String value_ZB_jdmc = relation_inners_map.get(key).toString();
                                        log.info("添加节点："+key_MB_jdmc);
                                        Element element_new =  element_MB.addElement(key_MB_jdmc);
                                        Element jd_ZB = e.element(value_ZB_jdmc);
                                        if(jd_ZB!=null){
                                            String jdValue = jd_ZB.getText() ;
                                            log.info("获取节点值jdValue："+jdValue);
                                            element_new.setText(jdValue);
                                        }
                                    }
                                }
                            }
                            log.info("特殊处理type:"+HnConstants.LISTTYPE1);
                        }else {
                            log.info("relation_inners_BZ[0]-->"+relation_inners_BZ[0]+";"+"relation_inners_BZ[1]-->"+relation_inners_BZ[1]);
                        }
                }



            }else{
                try {
                    Document doc  = DocumentHelper.parseText(bzbw);
                    //根据对应关系获取对应elemnet，然后塞值
                    Element element_MB =  getElementFromDocument(document,inner_relationship_MB);
                    Element element_BZ =  getElementFromDocument(doc,inner_relationship_BZ);
                    element_MB.setText(element_BZ.getText());
                } catch (DocumentException e) {
                    log.error(e);
                }
            }
        }
        log.info(document.asXML());
    }
}