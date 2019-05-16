package com.app.password;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年12月20日
 */
public class PasswordValidator {
	Log log = LogFactory.getLog(PasswordValidator.class);
	static int min =8;
	static int max=16;
			
	/*1，不能全部是数字
	2，不能全部是字母
	3，必须是数字或字母
	能同时满足上面3个要求*/
	static String passwordRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{"+min+","+max+"}$";

   public static void main(String[] args) {
			String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";		

			String value = "a1aaa";  // 长度不够 false
			System.out.println(value.matches(regex));

			value = "1111aaaa1111aaaaa";  // 太长 false
			System.out.println(value.matches(regex));

			value = "111111111"; // 纯数字 false
			System.out.println(value.matches(regex));

			value = "aaaaaaaaa"; // 纯字母 false
			System.out.println(value.matches(regex));

			value = "####@@@@#!"; // 特殊字符false
			System.out.println(value.matches(regex));

			value = "1111aaaa";  // 数字字母组合true
			System.out.println(value.matches(regex));

			value = "aaaa1111"; // 数字字母组合 true
			System.out.println(value.matches(regex));

			value = "aa1111aa";	// 数字字母组合true
			System.out.println(value.matches(regex));

			value = "11aaaa11";	// 数字字母组合 true
			System.out.println(value.matches(regex));

			value = "aa11aa11"; // 数字字母组合 true
			System.out.println(value.matches(regex));
		}
}
