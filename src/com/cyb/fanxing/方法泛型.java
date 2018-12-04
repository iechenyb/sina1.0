package com.cyb.fanxing;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年11月28日
 */
public class 方法泛型 {
	Log log = LogFactory.getLog(方法泛型.class);
	public static  <T1,T2,T3> Response<T1> test1(T2 t2,T3 t3){
		return null;
	}
	
	/*public <T1,T2,T3> static Response<T1> test2(T2 t2,T3 t3){
		return null;
	} 不可以这样定义
	*/
	
	public <T1,T2,T3>  Response<T1> test3(T2 t2,T3 t3){
		return null;
	}
	
	public <T> T test4(T t){
		return t;
	}
	
	/*public T test5(T t){
		return t;
	}*/
	
	/*public <T> test6(T t){
		return t;
	}*/
	
	
}
