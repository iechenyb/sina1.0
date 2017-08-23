package com.cyb.diffcult;

/**
 * 静态语句块和静态方法的执行是严格有序的！！！
 * 
 * @author DHUser
 *
 */
public class 静态语句块父类 {
	public static String name = "chenyb";
	public static 静态语句块父类 obj1 = new 静态语句块父类("父类-filed-1");

	@SuppressWarnings("static-access")
	public 静态语句块父类(String name) {
		this.name = name;
		System.out.println("父类构造方法:" + this.name);
	}

	static {
		System.out.println("父类静态语句块1：");
		name = "cyb";
	}

	static {
		System.out.println("父类静态语句块2:");
	}

	public static 静态语句块父类 obj2 = new 静态语句块父类("父类-filed-2");

	public static void main(String[] args) {
		// new 静态语句块父类();
	}
}
