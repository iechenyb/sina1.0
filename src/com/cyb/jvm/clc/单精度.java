package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 单精度 {
	Log log = LogFactory.getLog(单精度.class);
	public static void main(String[] args) {
		float f1 = 100f;
		float f2 = 100f;
		float f3 = 500f;
		float f4 = 500f;
		System.out.println(f1==f2);//true
		System.out.println(f3==f4);//true
		Float F1 = 1.0f;
		Float F2 = 1.0f;
		Float F3 = 700.0f;
		Float F4 = 700.0f;
		System.out.println(F1==F2);//false
		System.out.println(F3==F4);//false
	}
}
