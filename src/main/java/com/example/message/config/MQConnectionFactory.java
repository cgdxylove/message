package com.example.message.config;

import lombok.extern.log4j.Log4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * @author cg
 * @create 2019-03-20 17:41
 */
@Component
@Log4j
public class MQConnectionFactory {
    @Value("${spring.activemq.broker-url}")
    private  String url;

    private static Connection connection ;

    public Connection getSingleConnection(){
        if(connection==null){
            synchronized (MQConnectionFactory.class){
                if(connection==null){
                    System.out.println("###################### 创建单例connection ##############");
                    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
                    try {
                        connection = connectionFactory.createConnection();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection ;
    }
}