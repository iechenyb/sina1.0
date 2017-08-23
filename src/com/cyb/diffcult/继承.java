package com.cyb.diffcult;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;

public class 继承 {// 单
	public static void main(String[] args) {
		Child c = new Child();
		c.def();
		c.pro();
		// c.pri();//是有方法不能被继承
		c.pub();
	}
}

class Cl {
}

class C2 {
}

class Parent {
	static {
		System.out.print("A");
	}

	public Parent() {
		System.out.print("a");
	}

	static {
		System.out.print("A1");
	}

	void f() throws Exception {
	};

	// void f1();//必须抛出异常
	// void f2()throws IOException{};//ERROR
	// 继承时抛出父类异常正确,可以抛出父类没的异常
	void f2() throws Exception, FileNotFoundException {
	};

	private void pri() {
	}

	protected void pro() {
	}

	void def() {
	}

	public void pub() {
	}
}

class Child extends Parent {// 类继承只能单继承，多继承会报错Parent,C1,C2，
	static {
		System.out.print("B");
	}

	public Child() {
		System.out.print("b");
	}

	void f() throws Exception {
	};

	void f1() throws Exception {
	};

	/*void f2() throws Exception, IOException {
	};*///正确
	/*void f2() throws IOException {
	};*///正确
	/*void f2() {//子类继承可以不抛出父类声明的任何异常 //正确
	};*/
	void f2() throws FileSystemException{};//抛出父类没有声明的异常也可以 正确。
	static {
		System.out.print("B1");
	}

	// private void def(){};//继承缺省的方法报错 err
	// void def(){};//继承缺省的，可以是default的访问权限 正确
	// public void def(){};//正确
	protected void def() {
	}//// 正确
}
