package com.cyb.String;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月16日
 * http://www.blogjava.net/nokiaguy/archive/2008/05/07/198990.html
 */
public class 字符串比较 {
	Log log = LogFactory.getLog(字符串比较.class);
    static final String fstr1="a";
    static final String fstr2="bc";
    static  String nfstr1="a";
    static  String nfstr2="bc";
	public static void main(String[] args) {
		String str = "abc";
		String str1 = "a";
		String str2 = "bc";
		final String fstr1="a";
		final String fstr2="bc"; 
		String combo0 = fstr1+fstr2;
		String combo1 = "a" + "bc"; //true 第一种情况 字节码class中combol1=abc
		String combo2 = str1 + str2; //false 第二种情况 字节码中combo2=str1+str2
		String combo3 = fstr1+fstr2;//true
		String combo4 = nfstr1+nfstr2;//false
		String combo5="a"+str2;//false字符串变量与常量字符串相加
		String combo6 ="a"+fstr2;//最终变量 true
		String combo7 ="a"+nfstr2;//非最终变量 false
		System.out.println(str == combo0);//true
		System.out.println(str == combo1);//true
		System.out.println(str == combo2);//false
		System.out.println(str == combo3);//true
		System.out.println(str == combo4);//false
		System.out.println(str == combo5);//false
		System.out.println(str == combo6);//true
		System.out.println(str == combo7);//false
	}
}
