package com.cyb.youhua;

public class 阶乘 {
	public static long jiecheng(long num){
		if(num<=0){return 0;}
		if(num==1){
			return 1;
		}else{
			return num*jiecheng(num-1);
		}
	}
	public static void main(String[] args) {
		System.out.println(jiecheng(5));
	}
}
