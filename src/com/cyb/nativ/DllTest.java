package com.cyb.nativ;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月20日
 */
public class DllTest {
	static {
		System.loadLibrary("myString");
	}	
	public native void helloWorld();
	public static void main(String[] s){
		DllTest test = new DllTest();
		test.helloWorld();
	}
}
