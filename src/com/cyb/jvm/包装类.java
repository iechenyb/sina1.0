package com.cyb.jvm;

public class 包装类 {
  public static void main(String[] args) {
	  short s1=1;
	  /*s1=s1+1;*/// err 1是int s1是short result should is int
	  int s2 = s1+1;
	// 2.String类型也实现了常量池技术，但是稍微有点不同。String型是先检测常量池中有没有对应字符串，如果有，则取出来；如果没有，则把当前的添加进去。
			// 代码1
			String sa = "ab";
			String sb = "cd";
			String sab = sa + sb;
			String s = "abcd";
			System.out.println(sab == s); // false
			// 代码2
			String sc = "ab" + "cd";
			String sd = "abcd";
			System.out.println(sc == sd); // true
			// 代码1
			String sa1 = new String("Hello world");
			String sb1 = new String("Hello world");
			System.out.println(sa1 == sb1); // false
			// 代码2
			String sc1 = "Hello world";
			String sd1 = "Hello world";
			System.out.println(sc1 == sd1); // true
			/**
			 * 代码1中局部变量sa1,sb1中存储的是JVM在堆中new出来的两个String对象的内存地址。
			 * 虽然这两个String对象的值(char[]存放的字符序列)都是"Hello world"。 因此"=="比较的是两个不同的堆地址。
			 * 代码2中局部变量sc1,sd1中存储的也是地址，但却都是常量池中"Hello world"指向的堆的唯一的那个拘留字符串对象的地址
			 * 。自然相等了。
			 */
			String ssab = "chenyb".intern();
			String ssac = "chenyb".intern();
			String ssad = new String("chenyb").intern();
			String ssae = new String("chenyb");
			System.out.println(ssab == ssac);
			System.out.println(ssac == ssad);
			System.out.println(ssac == ssae);// false
  }
}
