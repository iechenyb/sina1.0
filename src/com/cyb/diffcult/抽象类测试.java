package com.cyb.diffcult;
abstract class 抽象类1{}
public class 抽象类测试 extends 抽象类 {
	public String name = "我在实现类里";
	public String x = "111";
	public String y = "222";

	public static void main(String[] args) {
		抽象类测试 obj = new 抽象类测试();
		 抽象类 xx = new 抽象类测试();
		System.out.println("this.name="+obj.name);
		obj.print1();
		obj.print2();//实现方法，
		xx.print();
		xx.print1();
		xx.print2();
	}

	public void print1() {
		System.out.println("print1方法被重写！super.name=" + super.name);
	}

	@Override
	public void print3() {
		print();
	}
	public void print2(final String f1,final String f2){
		//抽象类方法被重写
	};

	@Override
	public void jkprint() {
		System.out.println("jkprint");
	}

	@Override
	public void jkprint1() {
		System.out.println("jkprint1");
	}

	@Override
	public void statMethod() {

	}

	@Override
	protected void privateAbsMethod1() {

	}

	@Override
	public void privateAbsMethod12() {

	}
}
