package com.cyb.diffcult.接口多继承;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月25日
 *接口与抽象类很类似，直接列出区别点如下：
	1）接口中的属性都是常量（默认：public static final不写的话，系统自动加上），必须
	给定初始值，接口的函数都是抽象函数（默认：public abstract不写的话，系统自动加上）
	2）定义接口时不用class，而是使用interface
	3）接口可以继承多个父接口。接口继承的是接口，类继承的是类，这点很清楚，我们是用类去
	实现接口（implements,一个类可以实现多个接口，必须把接口中所有抽象函数的函数体都实现）
	4）接口没有构造函数，没有内置的this，super关键字
	 */
public class ChildHasFather  implements Father {
	Log log = LogFactory.getLog(ChildHasFather.class);

	@Override
	public void high() {
		System.out.println("继承了爸爸的高大");
	}
	
	public static void main(String[] args) {
		System.out.println(ChildHasFather.class.getInterfaces());
		ChildHasFather  child = new ChildHasFather();
		Class<?>[] cls = ChildHasFather.class.getInterfaces();
		for(int i=0;i<cls.length;i++){
			//遍历继承的接口
			System.out.println(cls[i].toString()+"->"+cls[i].isAssignableFrom(child.getClass()));
			if(cls[i].toString().contains("Father")){
				child.high();
			}
		}
		Father father = child;
		father.high();
	}

	@Override
	public void some() {
		
	}
}
