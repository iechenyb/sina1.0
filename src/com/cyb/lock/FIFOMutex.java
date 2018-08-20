package com.cyb.lock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月15日
 */
public class FIFOMutex {
	Log log = LogFactory.getLog(FIFOMutex.class);
	public void lock(){
		System.out.println(Thread.currentThread().getName());
	}
	public void unlock(){
		System.out.println(Thread.currentThread().getName());
	}
	public void lock(String msg){
		System.out.println(msg+":"+Thread.currentThread().getName());
	}
	public void unlock(String msg){
		System.out.println(msg+":"+Thread.currentThread().getName());
	}
}
