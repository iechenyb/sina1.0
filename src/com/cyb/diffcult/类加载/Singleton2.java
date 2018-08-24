package com.cyb.diffcult.类加载;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 常量分 普通常量 和静态常量
 * 作者 : iechenyb<br>
 * 类描述: final 修饰的为常量 子类继承父类时的初始化顺序 
 * 1.首先初始化父类的static变量和块，按出现顺序
 * 2.初始化子类的static变量和块，按出现顺序 
 * 3.初始化父类的普通变量，调用父类的构造函数 
 * 4.初始化子类的普通变量，调用子类的构造函数<br>
 * 创建时间: 2018年1月18日
 */
public class Singleton2 {
	Log log = LogFactory.getLog(getClass());
	public static final int a = init(5, "static final int a");
	public static int counter1;// 在构造方法*
	public static int counter2 = init2("static int c2", 120);// 在构造方法执行之后执行
	public final int c = init(6, "final int c");
	public int counter3 = init3("int c3", 110);// 初始化-最后
	public static final int b = init(4, "static final int b");
	public final int d = init(7, " final int d");
	public static int counter4 = init2("static int c4", 0);// 在构造方法执行之后执行
    private static void innerStaticMehtod(){}
	static {
		System.out.println("静态语句块1");
		Singleton2.innerStaticMehtod();//内部使用，不能被外部使用！！！
	}

	private Singleton2(String msg) {
		System.out.println(msg);
	}

	private static int init(int num, String bs) {
		System.out.println("初始化常量" + bs);
		return num;
	}

	private Singleton2() {
		System.out.println("构造方法执行！");
		++counter1;
		++counter2;
		++counter3;
	}

	static {
		System.out.println("静态语句块2");
	}
	private static Singleton2 sin = new Singleton2();// 与静态语句块按照顺序执行

	public static int init2(String msg, int val) {
		System.out.println(msg);
		return val;
	}

	public int init3(String msg, int val) {
		System.out.println(msg);
		return val;
	}

	public static Singleton2 getInstance() {
		return sin;
	}

	public static void main(String[] args) {
		Singleton2 sin = Singleton2.getInstance();
		System.out.println(sin.counter1);// 1
		System.out.println(sin.counter2);// 1
		System.out.println(sin.counter3);// 110->666
		
	}
	/**
	 *  初始化常量static final int a
		static int c2
		初始化常量static final int b
		static int c4
		静态语句块1
		静态语句块2
		初始化常量final int c
		int c3
		初始化常量 final int d
		构造方法执行！
		1
		121
		111
	 */
}
