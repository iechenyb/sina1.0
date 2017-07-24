package com.cyb.nativ;
/**
 * Can't load IA 32-bit .dll on a AMD 64-bit platform
 *
 *
 */
public class CalcDemo {
	
	 static {
		 System.out.println(System.getProperty("java.library.path"));
		 System.loadLibrary("CalcClass");
		 System.loadLibrary("CalcDemo");
	 }	 
	 
	 public static native  int MySub( int x,int y) ;
	 
	 public static void main(String[] args) {
		System.out.println(System.getProperty("java.library.path"));
		int ret = MySub(1, 2);
		System.out.println(ret);
	}
}
