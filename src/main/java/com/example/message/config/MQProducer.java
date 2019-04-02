package com.example.message.config;

import com.example.message.Constant.MQconstant;
import groovy.util.logging.Log4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
     * ActiveMQ服务提供
     * @author cg
     */
@Log4j
@Component
public class MQProducer{
        @Autowired
        private JmsMessagingTemplate jmsMessagingTemplate;
        @Resource
        MQTransactionSessionFactory mqTransactionSessionFactory;
        @Resource
        MQConnectionFactory mqConnectionFactory;

        private static Destination defaultMailDestination = new ActiveMQQueue(MQconstant.MAILQUEUE);

        public void sendMailQueue(String message){
            System.out.println("发送消息："+message);
            // 创建一个Queue的Destination，参数为Destination的名称
            jmsMessagingTemplate.convertAndSend(defaultMailDestination,message);
        }

        public void sendQueue(String message,String name){
            System.out.println("发送消息："+message);
            // 创建一个Queue的Destination，参数为Destination的名称
            Destination destination = new ActiveMQQueue(name);
            jmsMessagingTemplate.convertAndSend(destination,message);
        }

        public void sendTransationQueue(String message,String name){
            try {
                    Connection connection = mqConnectionFactory.getSingleConnection();
                    connection.start();
                    Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
                    Destination destination =  session.createQueue(name);
                    MessageProducer producer = session.createProducer(destination);
                    TextMessage textMessage = session.createTextMessage(message);
                    producer.send(textMessage);
                    System.out.println("发送事务消息："+textMessage.getText());
                    session.commit();
                    //jmsMessagingTemplate.convertAndSend(destination,message);
            } catch (JMSException e) {
                    e.printStackTrace();
            }

        }


        public void sendTopic(String message,String name){
            System.out.println("发送消息："+message);
            Destination destination = new ActiveMQTopic(name);
            jmsMessagingTemplate.convertAndSend(destination,message);
        }

}