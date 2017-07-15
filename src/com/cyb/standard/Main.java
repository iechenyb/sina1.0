package com.cyb.standard;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *  作者 : iechenyb<br>
 *  功能描述: 说点啥<br>
 *  创建时间: 2017年7月15日
 */
public class Main {
	Log log = LogFactory.getLog(Main.class);
	public static void main(String[] args) {
		StandardClass cls = new StandardClass();
		cls.method();
		cls.method("1", 2);
	}
}
