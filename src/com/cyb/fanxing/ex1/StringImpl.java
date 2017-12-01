package com.cyb.fanxing.ex1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class StringImpl implements AnyTypeInterface<String>{
	Log log = LogFactory.getLog(StringImpl.class);

	@Override
	public String getVar() {
		return "chenyb";
	}
	
	public static void main(String[] args) {
		AnyTypeInterface<String> any = new StringImpl();
		System.out.println(any.getVar());
	}
}
