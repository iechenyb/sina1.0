package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.diffcult.other.PublicCls;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月23日
 */
public class 访问权限 {
	Log log = LogFactory.getLog(访问权限.class);
	public static void main(String[] args) {
		//DefaultCls x;//跨包时，default的类不能被访问
		PublicCls pc = new PublicCls();
		String pubStr= pc.pubStr;
		String priStr = pc.getPriStr();
		String defStr = pc.getDefStr();
		String proStr = pc.getProStr();//除了public成员其他的只能通过方法进行调用
		PublicCls.pubStaticMethod();//静态方法只有一个public权限的可见。
		
	}
}
