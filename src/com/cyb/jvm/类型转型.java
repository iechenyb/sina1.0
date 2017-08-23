package com.cyb.jvm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月20日
 */
public class 类型转型 {
	Log log = LogFactory.getLog(类型转型.class);
	public static void main(String[] args) {
		short s1 = 1;
		s1+=1;//编译后会强制转型
		byte b1 = 1;
		b1+=1;//编译后会强制转型
		int i1=1;
		i1+=1;
		long l1 = 1;
		l1+=1;
		@SuppressWarnings("unused")
		int a = (int) (1+2l);
		System.out.println(s1+","+b1+", "+i1+","+l1);
	}
}
