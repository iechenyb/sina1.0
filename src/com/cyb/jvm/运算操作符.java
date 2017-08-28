package com.cyb.jvm;

public class 运算操作符 {
@SuppressWarnings("unused")
public static void main(String[] args) {
    System.out.println((1==1)||(2==2));//true
    System.out.println(1==1|2==2);//true
    System.out.println(false|false);//false
    System.out.println(false||false);//false
    System.out.println(true|false);//true
    System.out.println(true||false);//true
    System.out.println(3|2);//3
    System.out.println(1|1);//1
    System.out.println(13&12);//12
    System.out.println(1&1);//1
    System.out.println(12>>1);//6
    System.out.println(12<<1);//24无符号
    System.out.println(-12>>1);//-6
    System.out.println(-12<<1);//-24
    
    System.out.println(12>>>1);//
    //System.out.println(12<<<1);//无符号
    System.out.println(-12>>>1);//
    //System.out.println(-12<<<1);//
    String b20 = Integer.toBinaryString(20);//00010100
    System.out.println(b20);//10100
    System.out.println(20>>1);//10
    System.out.println(20<<1);//40
    System.out.println(20>>>1);//10
    System.out.println(-20>>>1);//2147483638
	}
}
