package com.cyb.diffcult.类加载;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月18日
 */
public class Singleton2 {
	Log log = LogFactory.getLog(getClass());
	public static int counter1;//在构造方法*
	public static int counter2 = init2("静态域初始化",0);//在构造方法执行之后执行
	public int counter3 = init3("非静态域初始化",0);//最先初始化
	private static Singleton2 sin = new Singleton2();//"初始化！"
	private Singleton2(String msg) {
		System.out.println(msg);
	}
	private Singleton2() {
		System.out.println("构造方法执行！");
		++counter1;
		++counter2;
	}
    public static int init2(String msg,int val){
    	System.out.println(msg);
    	return val;
    }
    public  int init3(String msg,int val){
    	System.out.println(msg);
    	return val;
    }
	public static Singleton2 getInstance() {
		return sin;
	}
	public static void main(String[] args) {
		Singleton2 sin = Singleton2.getInstance();
		System.out.println(sin.counter1);//1
		System.out.println(sin.counter2);//1
		System.out.println(sin.counter3);//0
		//错误答案 1 1 
		//初始化顺序   非静态域->构造方法->静态域
	}
	/**
	 *  非静态域初始化
		构造方法执行！
		静态域初始化
		1
		0
		0
	 */
}

