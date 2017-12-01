package com.cyb.annotation.spring;

import java.lang.reflect.Method;
/**
 * 模拟spring容器
 * @author DHUser
 *
 */

public class SpringContainer {
	public static LongServiceTest getBean() {  
		LongServiceTest serivce = new LongServiceTest();  
		//遍历servic服务组件
        if (LongServiceTest.class.isAnnotationPresent(AnnotationService.class)) {  //service 组件
           //遍历方法
        	Method[] methods = LongServiceTest.class.getDeclaredMethods();  
            for (Method method : methods) {  
                System.out.println(method);  
                //如果是注入注解，则根据指定的类型进行注入
                if (method.isAnnotationPresent(AnnotationIOP.class)) {  
                	AnnotationIOP annotest = method.getAnnotation(AnnotationIOP.class);  
                    System.out.println("AnnotationTest(field=" + method.getName()  
                            + ",className=" + annotest.className() + ")");  
                    try {  
                    	IUser userdao = (IUser) Class.forName("com.cyb.annotation.spring." + annotest.className()).newInstance();  
                    	/*if(annotest.className().equals("ChineseUserImpl")){
	                        userdao = (IUser) Class.forName("com.cyb.annotation.spring." + annotest.className()).newInstance();  
	                        serivce.setUserdao(userdao);  
                        }else{
                        	 userdao = (IUser) Class.forName("com.cyb.annotation.spring." + annotest.className()).newInstance();  
 	                         serivce.setUserdao1(userdao);  
                        }*/
                    	method.invoke(serivce, Class.forName("com.cyb.annotation.spring." + annotest.className()).newInstance());//回调当前service的method，注入dao
                    } catch (Exception ex) {  
                       ex.printStackTrace(); 
                    }  
                }  
            }  
        } else {  
            System.out.println("没有注解标记！");  
        }  
        return serivce;  
    }  
}
