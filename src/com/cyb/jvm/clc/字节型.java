package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *作者 : bechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 字节型 {
	Log log = LogFactory.getLog(字节型.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		byte b40 = 40;
		byte b41 = 40;
		byte b127 = 127;//最大值
		//byte b128 = 128;<=127
		byte b128_=-128;//最小值
		//byte b129_=-129;>=-128
		Byte B40=Byte.valueOf(b40);
		Byte B41=Byte.valueOf(b41);
		System.out.println("b40=b41\t" + (b40 == b41));//true
		System.out.println("B40=B41\t" + (B40 == B41));//true
		Byte B200=Byte.valueOf("200");
		Byte B201=Byte.valueOf("200");
		System.out.println("B200=B201\t" + (B200 == B201));//java.lang.NumberFormatException: Value out of range. Value:"200" Radix:10
	}
}
