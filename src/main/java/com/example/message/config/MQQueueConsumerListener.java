package com.example.message.config;

import com.example.message.Constant.MQconstant;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author cg
 * @create 2019-03-25 10:01
 */
public class MQQueueConsumerListener implements MessageListener {
     @Override
     public void onMessage(Message message) {
         TextMessage tm = (TextMessage)message;
         try {
             System.out.println("获取MQ中数据信息->" +  tm.getText());
         }catch (Exception e) {
             e.printStackTrace();
         }
     }

     // 值为要接收的destination的名称
    @JmsListener(destination = MQconstant.MAILQUEUE)
    public void receiveMailQueue(String text) {
        System.out.println("接收到的邮件消息:" + text);
    }
}