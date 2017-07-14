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
	String str="1234567890";
	StringBuilder sb = new StringBuilder(str);
	System.out.println(sb.replace(2, 8, "******"));
 }
 
}
