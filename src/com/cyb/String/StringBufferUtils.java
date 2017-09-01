package com.cyb.String;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月1日
 */
public class StringBufferUtils {
	Log log = LogFactory.getLog(StringBufferUtils.class);
	public static void main(String[] args) {
		StringBuffer sb =new StringBuffer("0123456789");
		sb.insert(2, "abc");
		System.out.println(sb.toString());//01abc23456789
	}
}
