package com.cyb.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.activemq.cse.Contanst;

public class MessageReceiver implements Runnable{
	public static Log log = LogFactory.getLog(MessageReceiver.class);
	public static void main(String[] args) throws Exception {
		receiver();
    }
    public static void start(){
    	
    }

	public static void receiver() throws JMSException, InterruptedException{
    	//ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        String url = Contanst.SERVICEURL;
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	   
	    connectionFactory.setUserName("admin");
        connectionFactory.setPassword("amdin");
        Connection connection = connectionFactory.createConnection();
       
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("test");
        MessageConsumer consumer = session.createConsumer(destination);
        while (true) { 
        	Object obj = consumer.receive();
        	if(obj instanceof MapMessage){
            MapMessage message = (MapMessage) obj; 
            log.info(message.getLong("count"));
        	}else if(obj instanceof ObjectMessage){
        		ObjectMessage message = (ObjectMessage)obj;  
        		log.info(message.getStringProperty("count"));
        	}else if(obj instanceof ActiveMQTextMessage){
        		 ActiveMQTextMessage msg = (ActiveMQTextMessage)obj; 
        		 log.info(msg.getText());
        	}else{
        		log.info("消息类型解析失败！");
        	}
            session.commit();
            Thread.sleep(1000);
        }
        //session.close();
        //connection.close();    
    }
    
    
    
	@Override
	public void run() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			Connection connection = connectionFactory.createConnection();
			connection.start();
			final Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("my-queue");
			MessageConsumer consumer = session.createConsumer(destination);
			//consumer.setMessageListener(new JMSMSGListener(consumer));//set msg listen 鐩戝惉鍜岀洿鎺ユ帴鏀舵秷鎭簩鑰呭彧鑳介�夋嫨涓�涓�
			//consumer.receiveNoWait();
			while (true) {
			    MapMessage message = (MapMessage) consumer.receiveNoWait();
			    session.commit();
			    log.info("get message is " + message);
			}
			//session.close();
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}