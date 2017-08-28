package com.cyb.String;
/**
 * String：字符串常量 操作少量的数据用 
　 StringBuffer：字符串变量 线程安全的 多线程操作字符串缓冲区 下操作大量数据
　 StringBuilder：字符串变量 线程非安全的 单线程操作字符串缓冲区 下操作大量数据

 * @author DHUser
 *
 */
public class StringUtils {
 public boolean isEmpty(Object obj){
	 if(obj==null){ return true;}
	 if(org.springframework.util.StringUtils.isEmpty(obj)){
		 return true;
	 }
	 return false;
 }	
 public static void main(String[] args) {
	String str="0123456789";
	StringBuilder sb = new StringBuilder(str);
	//replace和substring只包含起点不包含终点
	System.out.println(sb.replace(2, 8, "******"));//01******89
	System.out.println(str.substring(2, 5));//234
	//多余的空格去掉，只留一个空格。
	String hello = "Hello    world,welcom    to shanghai !";
	System.out.println(hello.replaceAll("[\" \"]+", " "));
 }
 
}
