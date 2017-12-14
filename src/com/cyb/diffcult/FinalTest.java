package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月13日
 */
public class FinalTest {
	private final static String name;
	public String getName(){
		return name;
	}
	static{
		name="chenyb";
	}
	Log log = LogFactory.getLog(FinalTest.class);
	public static void main(String[] args) {
		System.out.println("name is "+name);
	}
}
