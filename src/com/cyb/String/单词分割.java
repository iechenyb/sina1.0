package com.cyb.String;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 单词分割 {
	Log log = LogFactory.getLog(单词分割.class);
	public static void main(String[] args) {
		String sentence = " i am a student !";
		//按照空格进行分割
		StringTokenizer st = new StringTokenizer(sentence, " ");  
		System.out.println();
		 while(st.hasMoreElements()){  
			 System.out.println("Token:" + st.nextToken());  
		 } 
	}
}
