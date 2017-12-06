package com.cyb.interfac;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.ui.GradientPaintTransformer;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class ChildSingleExtendsImpl implements FatherExtendGrandInterface {
	Log log = LogFactory.getLog(ChildSingleExtendsImpl.class);

	@Override
	public void grandMethod() {
		System.out.println("grandMethod");
	}

	@Override
	public void fatherMethod() {
		System.out.println("fatherMethod");
	}
	
	public static void main(String[] args) {
		FatherExtendGrandInterface father = new ChildSingleExtendsImpl();
		father.fatherMethod();
		father.grandMethod();//可以调用继承来的方法
		GrandInterface grand = new ChildSingleExtendsImpl();
		grand.grandMethod();
	}
}
