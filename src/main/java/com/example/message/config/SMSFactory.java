package com.example.message.config;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author cg
 * @create 2019-03-21 16:45
 */
@Component

public class SMSFactory {

    @Value("${sms.postMethod.utf8}")
    private String postMethod_utf8 ;

    @Value("${sms.Key}")
    private String key ;

    @Value("${sms.Uid}")
    private String Uid ;

    @Value("${sms.charset}")
    private String charset;


    /**
     * 批量发送同一个内容的短信
     * @param smsMobs
     * @param smsText
     * @return
     */
    public String sendSMSLot(String[] smsMobs,String smsText) throws IOException {
        //统一异常处理
        if(smsMobs == null||smsMobs.length<=0||smsMobs.length>100){
            return "400";
        }
        String smsMobStr = convertMobs(smsMobs);
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(postMethod_utf8);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+charset);//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", Uid),
                new NameValuePair("Key", key),
                new NameValuePair("smsMob",smsMobStr),
                new NameValuePair("smsText",smsText)};
        System.out.println(data);
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        return result;
    }

    private String convertMobs(String[] smsMobs) {
        StringBuffer buffer = new StringBuffer();
        int mLength = smsMobs.length ;
        for(int i=0;i<mLength;i++){
            if(i==mLength-1){
                buffer.append(smsMobs[i]);
            }else{
                buffer.append(smsMobs[i]).append(",");
            }
        }
        return buffer.toString();
    }


    public String sendSMSSingle(String smsMob,String smsText) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(postMethod_utf8);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+charset);//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", Uid),
                new NameValuePair("Key", key),
                new NameValuePair("smsMob",smsMob),
                new NameValuePair("smsText",smsText)};
        System.out.println(data);
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
        return result;
    }


    public static void main(String[] args) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn/");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", ""),
                new NameValuePair("Key", ""),
                new NameValuePair("smsMob",""),
                new NameValuePair("smsText","【来自XX的祝福】祝您身体健康，工作顺利，万事如意！")};
        System.out.println(data);
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        System.out.println(result); //打印返回消息状态
        post.releaseConnection();
    }


}