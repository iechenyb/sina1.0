package com.cyb.youhua;
/*1、1、2、3、5、8、13、21、34
F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）*/
public class 斐波拉契数列 {
	public static long fblq(int n){
		if(n==0||n==1) {
			return n;
		}else if(n>=2){ 
		   return fblq(n-1)+fblq(n-2);
		}else{//小于2最后判断
			return 0;
		}
		
	}
	public static void main(String[] args) {
		System.out.println(fblq(6));
		System.out.println(fblq(7));
		System.out.println(fblq(8));
	}
}
