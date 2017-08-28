package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 布尔 {
	Log log = LogFactory.getLog(布尔.class);
	public static void main(String[] args) {
		boolean b1 = true;
		boolean b2 = true;
		Boolean B1 = true;
		Boolean B2 = true;
		Boolean B3 = false;
		Boolean B4 = true;
		System.out.println("b1t==b2t \t"+(b1==b2));//true
		System.out.println("B1t==B2t \t"+(B1==B2));//true
		System.out.println("B3f==B4t \t"+(B3==B4));//false
		
		Boolean B5 = new Boolean(true);
		Boolean B6 = new Boolean(true);
		System.out.println("B5t==B6t \t"+(B5==B6));//false
		
		Boolean B7 = Boolean.valueOf(true);
		Boolean B8 = Boolean.valueOf(true);
		System.out.println("B7t==B8t \t"+(B7==B8));//true
		/**
		 *  b1t==b2t 	true
			B1t==B2t 	true
			B3f==b4t 	false
			B5t==B6t 	false
		 */
	}
}
