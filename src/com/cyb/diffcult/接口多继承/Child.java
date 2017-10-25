package com.cyb.diffcult.接口多继承;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月25日
 */
public class Child  implements Father,Monther {
	Log log = LogFactory.getLog(Child.class);

	@Override
	public void beautify() {
		System.out.println("继承了妈妈的漂亮");
	}

	@Override
	public void high() {
		System.out.println("继承了爸爸的高大");
	}
	
	public static void main(String[] args) {
		System.out.println(Child.class.getInterfaces());
		Child  child = new Child();
		Class<?>[] cls = Child.class.getInterfaces();
		for(int i=0;i<cls.length;i++){
			System.out.println(cls[i].isAssignableFrom(child.getClass()));
			if(cls[i].toString().contains("Father")){
				child.high();
			}
			if(cls[i].toString().contains("Monther")){
				child.beautify();
			}
		}
	}
}
