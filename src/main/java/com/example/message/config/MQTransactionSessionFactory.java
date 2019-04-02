package com.example.message.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author cg
 * @create 2019-03-20 16:10
 */
@Component
public class MQTransactionSessionFactory{

    @Value("${spring.activemq.broker-url}")
    private  String url;

    @Resource
    MQConnectionFactory mqConnectionFactory ;

    private static  Session singleSession;



    public  Session  getMQTransactionSingleSession() {
        if(singleSession==null){
            synchronized (MQTransactionSessionFactory.class){
                if(singleSession==null){
                    System.out.println("###################### 创建单例singleSession  ##############");
                    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                    Connection connection = null;
                    try {
                        connection = connectionFactory.createConnection();
                        connection.start();
                        singleSession = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return singleSession;
    }

    public  Session  getMQTransactionSession() {
        try {
            Connection connection = mqConnectionFactory.getSingleConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            return session;
        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }

    }

    public  Session  getMQSession() {
        try {
            Connection connection = mqConnectionFactory.getSingleConnection();
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            return session;
        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }

    }

}