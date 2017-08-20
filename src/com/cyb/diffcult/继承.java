package com.cyb.diffcult;

public class 继承 {
public static void main(String[] args) {
	System.out.println(new Parent()==new Child());
	Child c = new Child();
	c.print2(); //protectedl可见
	//c.print1();//不可见 私有方法
	c.print3();
	Parent p = new Child();
	p.print2(); //protectedl可见
	//p.print1();//不可见 私有方法,只能被其他方法使用，不能被类实例使用
	p.print3();
}
}
class Parent{
	private void print1(){
		System.out.println(" parent private method!");
	}
	protected void print2(){
		System.out.println(" parent protected method!");
	}
	 void print3(){
		print1();//私有方法只能在类内部使用。
		System.out.println(" parent default method!");
	}
}
class Child extends Parent{
	void print3(){
		System.out.println(" parent default method overide!");
	}
}
