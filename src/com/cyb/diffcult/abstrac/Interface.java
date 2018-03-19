package com.cyb.diffcult.abstrac;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月31日
 */
public interface Interface {
	/**
	 *作者 : iechenyb&lt;br&gt;
	 *类描述: 说点啥&lt;br&gt;
	 *创建时间: 2017年8月31日
	 */
	 int a=0;//接口中声明的变量都是final的，所以必须进行初始化！
	 void print();
	 abstract void absprint();
}
/**
 * 编译后代码
 * public abstract interface Interface
	{
	  public static final int a = 0;
	
	  public abstract void print();
	
	  public abstract void absprint();
	}
 */