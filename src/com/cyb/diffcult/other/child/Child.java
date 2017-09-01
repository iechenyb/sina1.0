package com.cyb.diffcult.other.child;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.diffcult.other.PublicCls;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月1日
 */
public class Child extends PublicCls{
	Log log = LogFactory.getLog(Child.class);
	public void testUseDefaultField(){
		System.out.println(this.getDefStr());//父类的缺省的属性不能被子类直接访问
	}
	public void testUseProtectedField(){
		System.out.println(this.proStr);//父类的受保护的属性不能被子类直接访问
	}
	public static void main(String[] args) {
		
	}
}
