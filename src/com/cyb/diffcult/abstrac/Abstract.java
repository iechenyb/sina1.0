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
	//抽象类可以不实现接口的方法，可以没有抽象方法或者是空类
}
