package com.cyb.diffcult.other;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.diffcult.other.PublicCls;
/**
 *作者 : iechenyb<br>
 *类描述: 同包内部测试<br>
 *创建时间: 2017年8月23日
 */
public class 访问权限  extends DefaultCls{
	public void test(){
		System.out.println(this.defStr);
		System.out.println(this.proStr);
	}
	Log log = LogFactory.getLog(访问权限.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		DefaultCls dc = new DefaultCls();//跨包时，default的类不能被访问
		
		String dpubStr= dc.pubStr;
		String defStr1 = dc.defStr;//包权限同一个包的其他类可以访问
		String dpros = dc.proStr;//protected方法只能在同包内访问
		//String dpris = dc.priStr;//不可见
		String dpriStr = dc.getPriStr();
		String ddefStr = dc.getDefStr();
		String dproStr = dc.getProStr();
		
		DefaultCls.pubStaticMethod();
		DefaultCls.defStaticMethod();
		DefaultCls.proStaticMethod();
		//DefaultCls.priStaticMethod();//同包不可见
		
		//DefaultCls.priStaticMethod();//private 即使同包也不可见
		PublicCls pc = new PublicCls();
		String pubStr= pc.pubStr;
		String priStr = pc.getPriStr();
		String defStr = pc.getDefStr();
		String proStr = pc.getProStr();//除了public成员其他的只能通过方法进行调用
		PublicCls.pubStaticMethod();//静态方法只有一个public权限的可见。
		
	}
}
