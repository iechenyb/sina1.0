package com.cyb.annotation.spring;

public class MyAnnotationMain {
    public static void main(String[] args) {  
    	//根据类型注入
    	LongServiceTest service = SpringContainer.getBean();  
        service.chineaselogin();
        service.englishlogin();
    }  
}
