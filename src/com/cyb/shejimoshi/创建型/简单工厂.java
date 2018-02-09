package com.cyb.shejimoshi.创建型;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月31日
 */
interface Sender {  
    public void Send();  
}  
class MailSender implements Sender {  
    @Override  
    public void Send() {  
        System.out.println("this is mail sender!");  
    }  
}  
class SmsSender implements Sender {  

    @Override  
    public void Send() {  
        System.out.println("this is sms sender!");  
    }  
}  
class SendFactory {  

    public Sender produce(String type) {  
        if ("mail".equals(type)) {  
            return new MailSender();  
        } else if ("sms".equals(type)) {  
            return new SmsSender();  
        } else {  
            System.out.println("请输入正确的类型!");  
            return null;  
        }  
    }  
} 
public class 简单工厂 {
	Log log = LogFactory.getLog(简单工厂.class);
	 public static void main(String[] args) {  
        SendFactory factory = new SendFactory();  
        Sender sender = factory.produce("mail");  
        sender.Send();  
	 }  
}
