package com.example.message;

import com.example.message.config.MQProducer;
import com.example.message.config.MQQueueConsumer;
import com.example.message.config.MQTransactionSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author cg
 * @create 2019-03-20 9:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTest {

    @Resource
    MQProducer producer;
    @Resource
    MQQueueConsumer queueConsumer ;
    @Resource
    private MQTransactionSessionFactory mqTransactionSessionFactory;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void producerTest(){
// 创建一个Queue的Destination，参数为Destination的名称
        for(int i = 0; i<10; i++){
            producer.sendQueue("这是一个json"+i,"queue-test");
        }
    }

    @Test
    public void trancationTest(){
        // 创建一个Queue的Destination，参数为Destination的名称
        for(int i = 0; i<10; i++){
            producer.sendTransationQueue("这是一个事务性测试"+i,"trancation1");
        }
    }


    @Test
    public void receiveTest() throws InterruptedException {
        queueConsumer.receive("queue-test");
    }

    @Test
    public void receiveThreadTest() throws InterruptedException {
        MQQueueConsumer mqQueueConsumer = new MQQueueConsumer("queue-test","");
        mqQueueConsumer.setMqTransactionSessionFactory(mqTransactionSessionFactory);
        mqQueueConsumer.setJmsMessagingTemplate(jmsMessagingTemplate);
        mqQueueConsumer.receive();
        //mqQueueConsumer.stopWork();
    }
}