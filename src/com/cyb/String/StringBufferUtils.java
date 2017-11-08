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
		//staticSb(1000000);//100万用时50-88ms  条用长度函数 75-130ms
		staticeq(100*10000);//4-6ms
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 统计sb作为临时变量擦除和赋值耗时<br>
	 *创建时间: 2017年7月15日
	 *@param num
	 */
	public static void staticSb(int num){
		long s = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<num;i++){
			sb.append("20180102");
			sb.delete(0, sb.length());//
		}
		long e = System.currentTimeMillis();
		System.out.println("共用时"+(e-s)%1000);
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 统计字符串变量比较耗时<br>
	 *创建时间: 2017年7月15日
	 *@param num
	 */
	public static void staticeq(int num){
		long s = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<num;i++){
			if(sb.equals("20140203")){}else{}
		}
		long e = System.currentTimeMillis();
		System.out.println("共用时"+(e-s)%1000);
	}
}
