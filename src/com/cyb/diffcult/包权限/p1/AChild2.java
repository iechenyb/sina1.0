package com.cyb.diffcult.包权限.p1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月3日
 */
public class AChild2{
	Log log = LogFactory.getLog(AChild2.class);
	
	public void visiSuperField(){
		A a = new A();
		System.out.println(a.pro+a.def);//同一个包访问（protected和default权限）
		//super.pri;//private变量不可见
		/*B b = new B();
		b.name;//跨包不可见
		b.age;//跨包不可见*/
	}
	public static void main(String[] args) {
		
	}
}
