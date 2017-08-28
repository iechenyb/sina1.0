package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 双精度 {
	Log log = LogFactory.getLog(双精度.class);
	public static void main(String[] args) {
		Double D1 = 1.0;
		Double D2 = 1.0;
		System.out.println("D1=D2\t" + (D1 == D2));//false
		double d1 = 100d;
		double d2 = 100d;
		System.out.println("d1=d2\t" + (d1 == d2));//true
	}
}
