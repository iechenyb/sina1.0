package com.cyb.thread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年4月3日
 */
class A {
	public void print(){
		System.out.println(Thread.currentThread().getName());
		new A1().print();
	}
}
class A1 {
	public void print(){
		System.out.println(Thread.currentThread().getName());
		new A11().print();
	}
}
class A11 {
	public void print(){
		System.out.println(Thread.currentThread().getName());
	}
}
public class ThreadName {
	Log log = LogFactory.getLog(ThreadName.class);
	public static void main(String[] args) {
		new A().print();//同一个入口净入的都是同一个线程名称
	}
}
