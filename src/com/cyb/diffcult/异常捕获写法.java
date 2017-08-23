package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月22日
 */
public class 异常捕获写法 {
	Log log = LogFactory.getLog(异常捕获写法.class);
	public static void main(String[] args) {
		try{}finally{}
		try{}catch(Exception e){}finally{}
		try{}catch(NullPointerException e){}catch(Exception e){}finally{} 
		try{}catch(NullPointerException e){}finally{} 
		try{}catch(Throwable t){}finally{}
		try{}catch(Error e){}finally{}
		//try{}catch(java.io.IOException e){}finally{}编译不通过
	}
}
