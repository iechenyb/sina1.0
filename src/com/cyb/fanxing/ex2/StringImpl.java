package com.cyb.fanxing.ex2;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class StringImpl extends AnyTypeClass<String>{
	Log log = LogFactory.getLog(StringImpl.class);

	@Override
	public String getVar() {
		return "chenyb";
	}
	
	public static void main(String[] args) {
		AnyTypeClass<String> any = new StringImpl();
		System.out.println(any.getVar());
	}
}
