package com.cyb.diffcult.abstrac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月31日
 */
public abstract class Abstract implements Interface{
	Log log = LogFactory.getLog(Abstract.class);
	/*抽象类可以不实现接口的方法,
	但是抽象类的子类必须实现抽象类及父接口的所有抽象方法。
	2可以没有抽象方法或者是空类*/
	public void absprint(){}//子类可以不用实现此方法，需要的时候重写此方法即可！
}
