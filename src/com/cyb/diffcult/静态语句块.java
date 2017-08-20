package com.cyb.diffcult;

/**
 * 静态语句块和静态方法的执行是严格有序的！！！ 接口中的无静态语句块，域定义对象不会执行！
 * 
 * @author DHUser
 *
 */
public class 静态语句块 extends 静态语句块父类 implements 接口 {
	public static String name = "chenyb";
	public static 静态语句块 obj1 = new 静态语句块("子类 field-1");
	@SuppressWarnings("static-access")
	public 静态语句块(String name) {
		super(name + "-子类调用父类构造器");
		this.name = name;
		System.out.println("子类构造方法：" + name );
	}

	static {
		System.out.println("子类静态语句块1：" );
		name = "cyb";
	}

	static {
		System.out.println("子类静态语句块2：" );
	}

	public static 静态语句块 obj2 = new 静态语句块("子类 field-2");

	public static void main(String[] args) {
		// new 静态语句块();
	}

	@Override
	public void jkprint() {
	}

	@Override
	public void jkprint1() {
	}
}
