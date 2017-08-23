package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月23日
 */
public class 字符串 {
	Log log = LogFactory.getLog(字符串.class);
	public static void main(String[] args) {
		String str="0123456789abcdef";
		System.out.println(str.subSequence(1, 4));
		str.replace("a", "x");
		System.out.println(str);//返回值被修改，但是元字符串没有被修改
		str.toLowerCase();
		System.out.println(str);
	}
}
