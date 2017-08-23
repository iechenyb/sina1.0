package com.cyb.diffcult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年8月22日
 */
public final class FinallTest extends A{
	Log log = LogFactory.getLog(FinallTest.class);
	public static void main(String[] args) {
		new A().print("XX");
		new A().print("1", "2");//final 修饰的方法可以被重载，不能被重写。
	}
	public void getName() {
	        
	}
	/*public final void  getName(String p1,String p2){
		
	}*/
}
/*class B extends FinallTest{
	//final 类不能被继承。
}*/
class A {
	 /**
     * 因为private修饰，子类中不能继承到此方法，因此，子类中的getName方法是重新定义的、
     * 属于子类本身的方法，编译正常
     */
	/*public final void getName() {
        //final修饰的方法不能被重写
	}*/
	public final void print(String name){
		
	}
    public final void print(String name,String name1){
		
	}
	private  final void print() {
        
	}
}