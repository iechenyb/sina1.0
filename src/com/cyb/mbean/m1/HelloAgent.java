package com.cyb.mbean.m1;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;  
import javax.management.ObjectName;  

import com.sun.jdmk.comm.HtmlAdaptorServer;  
/**
 * 运行后，浏览器可以查看mbean信息
 * @author DHUser
 *
 */
public class HelloAgent {
	 public static void main(String[] args) throws Exception {  
	        MBeanServer server = MBeanServerFactory.createMBeanServer();  
	        
	        ObjectName helloName1 = new ObjectName("service:name=HelloDynamic1");  
	        
	        ObjectName helloName2 = new ObjectName("service1:name=HelloDynamic2");  
	        
	        HelloDynamic hello = new HelloDynamic();  
	        
	        server.registerMBean(hello, helloName1); 
	        
	        server.registerMBean(hello, helloName2);  
	        
	        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082"); 
	        
	        HtmlAdaptorServer adapter = new HtmlAdaptorServer();  
	        
	        server.registerMBean(adapter, adapterName);  
	        
	        adapter.start();  
	        
	        System.out.println("start.....");  
	    }  
}
