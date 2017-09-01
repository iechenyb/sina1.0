package com.cyb.String;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月1日
 */
public class StringBuildUtils {
	Log log = LogFactory.getLog(StringBuildUtils.class);
	public static void main(String[] args) {
		StringBuilder sb =new StringBuilder("0123456789");
		sb.insert(2, "abc");//01abc23456789
		System.out.println(sb.toString());
	    int a[]={0,1,2,3,4,5,6,7,8,9};
	    System.out.println(Arrays.toString(a));
	    /*src:源数组；	srcPos:源数组要复制的起始位置；
	      dest:目的数组；destPos:目的数组放置的起始位置；length:复制的长度。*/
		System.arraycopy(a,1,a,5,2);//实现数组自己的复制，从a的第1位置开始，目标起始位置为5，复制两位数字
		System.out.println(Arrays.toString(a));
		/**
		 *  01abc23456789
			[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
			[0, 1, 2, 3, 4, 1, 2, 7, 8, 9]
		 */
	}
}
