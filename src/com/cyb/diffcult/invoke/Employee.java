package com.cyb.diffcult.invoke;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月31日
 */
public class Employee extends AbstractCallBack{

	public void doWork() {
		System.out.println("玩命干活中....");
		callBack.doEvent();
	}
	public void doWork_1() {
		System.out.println("玩命干活中....");
		callBack.doEvent(); 
	}
}