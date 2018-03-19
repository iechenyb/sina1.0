package com.cyb.diffcult.接口多继承;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月19日
 */
public class 接口继承判断 {
	Log log = LogFactory.getLog(接口继承判断.class);
	public static void main(String[] args) {
		Child child = new Child();
		show(child);
		System.out.println("***************");
		ChildHasFather ch = new ChildHasFather();
		show(ch);
		//作用：实现增强
	}
	public static void show(ChildHasFather child){
		boolean hasMonther = Monther.class.isAssignableFrom(child.getClass());
		boolean hasFather = Father.class.isAssignableFrom(child.getClass());
		boolean hasAncestor = Ancestor.class.isAssignableFrom(child.getClass());
		System.out.println(hasMonther+","+hasFather+","+hasAncestor);
		/*if(hasMonther){
			child.beautify();//漂亮
		}*/
		if(hasFather){
			child.high();//高
		}
		/*if(hasAncestor){
			child.handsome();//家族英俊
		}*/
	}
	public static void show(Child child){
		boolean hasMonther = Monther.class.isAssignableFrom(child.getClass());
		boolean hasFather = Father.class.isAssignableFrom(child.getClass());
		boolean hasAncestor = Ancestor.class.isAssignableFrom(child.getClass());
		System.out.println(hasMonther+","+hasFather+","+hasAncestor);
		if(hasMonther){
			child.beautify();//漂亮
		}
		if(hasFather){
			child.high();//高
		}
		if(hasAncestor){
			child.handsome();//家族英俊
		}
	}
}
