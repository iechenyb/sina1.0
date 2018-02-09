package com.cyb.diffcult.invoke;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月31日
 */
public class Employee extends AbstractListener{

	public void startWork() {
		System.out.println("玩命干活中....");
		//listener.doEvent();
		notifyAllPerson();
	}
	public void startWork_1() {
		System.out.println("玩命干活中....");
		//listener.doEvent();
		notifyAllPerson();
	}
}