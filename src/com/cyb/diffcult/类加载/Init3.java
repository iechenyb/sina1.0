package com.cyb.diffcult.类加载;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 
	test static
	myclass static
	person static
	person Test --父类成员变量初始化
	test constructor--父类的构造器
	person MyClass --子类成员变量初始化
	myclass constructor --子类构造器<br>
 *创建时间: 2018年8月20日
 */
public class Init3 {
	Log log = LogFactory.getLog(Init3.class);
	Person person = new Person("Test");
    static{
        System.out.println("test static");
    }
     
    public Init3() {
        System.out.println("test constructor");
    }
     
    public static void main(String[] args) {
        new MyClass();
    }
}
class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}
 
 
class MyClass extends Init3 {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }
     
    public MyClass() {
        System.out.println("myclass constructor");
    }
}
