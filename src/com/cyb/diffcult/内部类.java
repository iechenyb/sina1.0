package com.cyb.diffcult;

public class 内部类 {
	public String name = "chenyb";
	public static String name1 = "staticchenyb";
	public static Runnable task = new Runnable() {
		@Override
		public void run() {
			System.out.println("task run...");
		}
	};
	public B obj1 = new B();// 静态类对象创建
	public A obj = new A();// 静态类对象创建
	public static B sobj1 = new B();// 静态类对象创建
	public static A sobj = new 内部类().new A();// 非静态类对象创建

	class A implements 接口 {
		class A1 {
			class A11 {
				void print() {
					System.out.println("inner3," + name);
				}
			}
		}

		void print() {
			System.out.println("inner," + name);
		}

		@Override
		public void jkprint() {

		}

		@Override
		public void jkprint1() {

		}
	}

	public static class B {// 可以被jvm里所有其他的类访问
		static {
			System.out.println("static{}init," + name1);
		}

		public B() {
			System.out.println("static class init," + name1);
		}

		void print() {
			System.out.println("inner1," + name1);
		}
	}

	public void foo() {
		new A();
		new B();
	}

	public static void bar() {
		// this.xx;//err
		// new Inner();//err
		new 内部类().new A();
		new B();
		// new 内部类().new Inner1();//err
		// 内部类().new Inner1();//err
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// A为普通内部类 B为静态内部类。
		A i = new 内部类().new A();// 非静态内部类的创建
		内部类.B i1 = new 内部类.B();// 静态内部类的创建，就少一个new关键字
		// 内部类.Inner1().print();//错误
		// new 内部类.new B().print();//错误
		// new 内部类.A().print();//错误
		new 内部类().new A().print();
		new 内部类().new A().new A1().new A11().print();
		new B().print();
		System.out.println("$$$$$$$$$$$$$$$$");
		new 内部类().obj.print();
		new 内部类().obj1.print();
		sobj.print();
		sobj1.print();
	}
}
