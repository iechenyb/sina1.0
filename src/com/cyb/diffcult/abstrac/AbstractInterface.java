package com.cyb.diffcult.abstrac;

/**
 *作者 : iechenyb<br>
 *类描述:加抽象关键字的接口与接口本身定义没有区别。<br>
 *接口中的所有方法默认必须是非静态的，且是abstract的，且是public的
 *创建时间: 2017年8月31日
 */
public abstract interface AbstractInterface {
	/**
	 *作者 : iechenyb&lt;br&gt;
	 *类描述: 说点啥&lt;br&gt;
	 *创建时间: 2017年8月31日
	 */
	int a=0;//接口中声明的变量都是final的，所以必须进行初始化！
	static int b=1;
	void print();
	//public static void print_();//非法静态
	abstract void absprint();
	//public static abstract void staticMethod();//静态非法
}
/*
 //反编译后的java代码
	 public abstract interface AbstractInterface
	{
	  public static final int a = 0;
	  public static final int b = 1;
	
	  public abstract void print();
	
	  public abstract void absprint();
	}
 */