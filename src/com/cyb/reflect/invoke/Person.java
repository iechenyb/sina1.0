package com.cyb.reflect.invoke;

import java.lang.reflect.Method;  

public class Person {  
    public static void main(String[] args) throws Exception {         
        //Class<?> clazz = Class.forName("com.cyb.reflect.invoke.Person");  
    	Person person = new Person();
    	Class<?> clazz = person.getClass();
        // 调用Person类中的run方法  
        Method method = clazz.getMethod("run");  
        method.invoke(person);//clazz.newInstance()  
        // Java 反射机制 - 调用某个类的方法1.  
        // 调用Person的Speak方法  
        method = clazz.getMethod("Speak", int.class, String.class);  
        method.invoke(person, 22, "小明");  
        // Java 反射机制 - 调用某个类的方法2.  
        // age -> 22. name -> 小明  
    }  
    public void run(){  
        System.out.println("调用Person类的run方法");  
    }  
    public void Speak(int age, String name){  
        System.out.println("调用Person类的Speak方法");  
        System.out.println("age -> " + age + ". name -> " + name);  
    }  
  
}  
