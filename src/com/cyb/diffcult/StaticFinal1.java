package com.cyb.diffcult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月24日
 */


public class StaticFinal1{
	public static void main(String[] args) {
		// 经过编译优化，静态常量HELLO已经存到NotInit类自身常量池中，不会加载ConstC
		System.out.println(StaticFinal.HELLO);
		//静态语句块和默认构造方法都不执行
	}
}
 class StaticFinal {
	Log log = LogFactory.getLog(StaticFinal1.class);

	static {
		System.out.println("ConstC init!");
		//HELLO ="sdf";
	}

	public StaticFinal() {
		System.out.println("ConstC ");
		//HELLO = "123";
	}

	public static final String HELLO = "hello world!";

	/*public static void main(String[] args) {  默认构造函数会执行
		// 经过编译优化，静态常量HELLO已经存到NotInit类自身常量池中，不会加载ConstC
		System.out.println(StaticFinal.HELLO);
	}*/
}
