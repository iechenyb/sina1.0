package com.cyb.log;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月25日
 */
public class Service {
	Log log = LogFactory.getLog(Service.class);
	public static void print(String msg){
		Log log1 = LogFactory.getLog(MyLogUtil.class);
		log1.info("开始");//行数是当前类的行数，方法是当前类的方法，但是类是指定的类。
		System.out.println("service print msg is" +msg);
		log1.info("结束");
	}
}
