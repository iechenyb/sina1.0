package com.cyb.aop;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月22日
 */
public class GreetingImpl2   {
	Log log = LogFactory.getLog(GreetingImpl2.class);

	public void sayHello(String words) {
		System.out.println(words);
	}
}
