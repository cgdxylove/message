package com.example.message.config;

import com.example.message.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author cg
 * @create 2019-03-20 13:56
 */
//点对点接受信息和监听器异步接收信息不能共存
@Component
public class MQQueueConsumer  extends  Thread{
    @Resource
    private MQTransactionSessionFactory mqTransactionSessionFactory;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private String queueName ;
    private String topicName ;

    private  volatile  Boolean isWork =true;

    public void receive(String name) throws InterruptedException {
        Session session = mqTransactionSessionFactory.getMQSession();
        try {
            Destination destination =  session.createQueue(name);
            MessageConsumer consumer =  session.createConsumer(destination);
            System.out.println("isWork1:"+isWork);
            while(isWork){
                TextMessage message =(TextMessage) consumer.receive(10*1000);
                if(message!=null){
                    System.out.println("消息内容text:"+message.getText());
                    System.out.println("isWork2:"+isWork);
                    System.out.println("测试，暂停2s");
                    Thread.sleep(2000);
                }else{
                    break ;
                }
            }
            if(!isWork){
                System.out.println("被另外一个操作暂停了");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void receive() throws InterruptedException {
        String name = this.queueName ;
        if(StringUtils.isBlank(name)){
            System.out.println("没有获取目标Destination的名称。");
            return ;
        }
        Session session = mqTransactionSessionFactory.getMQSession();
        try {
            Destination destination =  session.createQueue(name);
            MessageConsumer consumer =  session.createConsumer(destination);
            System.out.println("isWork1:"+isWork);
            while(isWork){
                TextMessage message =(TextMessage) consumer.receive(10*1000);
                if(message!=null){
                    System.out.println("消息内容text:"+message.getText());
                    System.out.println("isWork2:"+isWork);
                    System.out.println("测试，暂停2s");
                    Thread.sleep(2000);
                }else{
                    break ;
                }
            }
            if(!isWork){
                System.out.println("被另外一个操作暂停了");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            receive(this.queueName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void stopWork(){
        try {
            System.out.println("stopWork暂停2s");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isWork = false ;
    }

    public void letWork(){
        this.isWork = true ;
    }

    public MQQueueConsumer() {
    }

    public MQQueueConsumer(String queueName, String topicName) {
        this.queueName = queueName;
        this.topicName = topicName;
        this.isWork = true;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Boolean getWork() {
        return isWork;
    }

    public void setWork(Boolean work) {
        isWork = work;
    }

    public MQTransactionSessionFactory getMqTransactionSessionFactory() {
        return mqTransactionSessionFactory;
    }

    public void setMqTransactionSessionFactory(MQTransactionSessionFactory mqTransactionSessionFactory) {
        this.mqTransactionSessionFactory = mqTransactionSessionFactory;
    }

    public JmsMessagingTemplate getJmsMessagingTemplate() {
        return jmsMessagingTemplate;
    }

    public void setJmsMessagingTemplate(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }
}
