package com.cyb.interfac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class ChildMultiExtendImpl implements FatherInterface,GrandInterface {
	Log log = LogFactory.getLog(ChildMultiExtendImpl.class);

	@Override
	public void grandMethod() {
		System.out.println("grandMethod");
	}

	@Override
	public void fatherMethod() {
		System.out.println("fatherMethod");
	}
	
	public static void main(String[] args) {
		FatherInterface father = new ChildMultiExtendImpl();
		father.fatherMethod();
		GrandInterface grand = new ChildMultiExtendImpl();
		grand.grandMethod();
	}
}
