package com.cyb.diffcult.包权限.p1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.diffcult.包权限.p2.B;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月3日
 */
public class AChild extends A{
	Log log = LogFactory.getLog(AChild.class);
	
	public void visiSuperField(){
		A a = new A();
		System.out.println(a.pro+a.def);//同一个包访问（protected和default权限）
		System.out.println(super.pro+","+super.def);//访问父类的属性（default和protected）
		//super.pri;//private变量不可见
	}
	public static void main(String[] args) {
		A a = new AChild();
		/*System.out.println(a.age);//不可以直接访问父类的default属性
		System.out.println(a.name);//不可以直接访问父类的protected属性*/
		B b = new B();
		/*b.name;//不可见
		b.age;//不可见*/
	}
}
