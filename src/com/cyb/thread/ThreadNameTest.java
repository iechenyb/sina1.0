package com.cyb.thread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月15日
 */
public class ThreadNameTest {
	Log log = LogFactory.getLog(ThreadNameTest.class);
	public static void main(String[] args) {
		//thread-nextThreadNum(); 线程编号是static的，每个thread对象公用
		Thread t1  = new Thread(new  Runnable() {
			public void run() {
				System.out.println("th1 :"+Thread.currentThread().getName());
			}
		});
		t1.start();
		System.out.println(t1.getId());
		Thread t2  =new Thread(new  Runnable() {
			public void run() {
				System.out.println("th2 :"+Thread.currentThread().getName());
			}
		});t2.start();System.out.println(t2.getId());
		Thread t3  =new Thread(new  Runnable() {
			public void run() {
				System.out.println("th3 :"+Thread.currentThread().getName());
			}
		});t3.start();System.out.println(t3.getId());
		Thread t4  =new Thread(new  Runnable() {
			public void run() {
				System.out.println("th4 :"+Thread.currentThread().getName());
			}
		});t4.start();System.out.println(t4.getId());
	}
}
