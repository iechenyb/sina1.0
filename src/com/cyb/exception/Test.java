package com.cyb.exception;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月15日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);
	public static void main(String[] args) {
		try{
			int i = 1/0;
		}catch(Exception e){
			
			System.out.println(e.getCause().getMessage());
		}
	}
}
