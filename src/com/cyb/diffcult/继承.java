package com.cyb.diffcult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class 继承 {

@SuppressWarnings("static-access")
public static void main(String[] args) {
	Child c = new Child();
	c.print2(); //protectedl可见
	//c.print1();//不可见 私有方法
	c.print3();
	c.staticMethod();//执行重写的静态方法
	Parent p = new Child();
	p.print2(); //protectedl可见
	//p.print1();//不可见 私有方法,只能被其他方法使用，不能被类实例使用
	p.print3();
	p.staticMethod();//重写的静态方法不可见，调用父类自己的静态方法。
}
}
class Parent{
	 static Log log = LogFactory.getLog(Parent.class);
	private void print1(){
		log.info(" parent private method!");
	}
	protected void print2(){
		log.info(" parent protected method!");
	}
	 void print3(){
		print1();//私有方法只能在类内部使用。
		log.info(" parent default method!");
	}
	 static void  staticMethod(){
		 log.info(" parent default static method!");
	 }
}
class Child extends Parent{
	static Log log = LogFactory.getLog(Child.class);
	void print3(){
		log.info(" child default method overide!");
	}
	static void  staticMethod(){
		log.info(" child default static method overide!");
	}
}
