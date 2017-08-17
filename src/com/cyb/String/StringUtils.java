package com.cyb.String;

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
 }
 
}
