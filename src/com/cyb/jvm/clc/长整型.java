package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 长整型 {
	Log log = LogFactory.getLog(长整型.class);
	public static void main(String[] args) {
		long l1=100;
		long l2 =100;
		long l3=800;
		long l4=800;
		System.out.println(l1==l2);//true
		System.out.println(l3==l4);//true
		Long L1=127L;
		Long L2=127l;
		Long L3=128l;
		Long L4=128l;
		System.out.println(L1==L2);//true max
		System.out.println(L3==L4);//false max+1
		Long L5=-128L;
		Long L6=-128l;
		Long L7=-129l;
		Long L8=-129l;
		System.out.println(L5==L6);//true min
		System.out.println(L7==L8);//false min+1
		
	}
}
