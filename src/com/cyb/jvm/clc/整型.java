package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 整型 {
	Log log = LogFactory.getLog(整型.class);
	public static void main(String[] args) {
		int i40 = 40;
		int i41 = 40;
		int i540 = 540;
		int i541 = 540;
		Integer i1 = 40;
		Integer i2 = 40; // java在编译的时候,被翻译成-> Integer i2 = Integer.valueOf(40);
		Integer i3 = 0;
		Integer i4 = new Integer(40);
		Integer i5 = new Integer(40);
		Integer i6 = new Integer(0);		
		Integer i7 = -129;
		Integer i8 = -129; // 常量池范围 -128<x<=127		
		Integer i9 = Integer.valueOf(200);
		Integer i10 = Integer.valueOf(200);		
		Integer i11 = new Integer(200);
		Integer i12 = new Integer(160);
		Integer i13 = new Integer(40);		
		// 对于i1==i2+i3、i4==i5+i6结果为True，是因为，Java的数学计算是在内存栈里操作的，Java会对i5、i6进行拆箱操作，其实比较的是基本类型（40=40+0），他们的值相同，因此结果为True。
		Integer i14 = new Integer(1000);
		Integer i15 = new Integer(500);
		Integer i16 = new Integer(500);
		System.out.println("i=i0\t" + (i40 == i41));//true
		System.out.println("i1=i2\t" + (i1 == i2));//true
		System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));//true 自动拆装箱
		System.out.println("i4=i5\t" + (i4 == i5));//false
		System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));//true
		System.out.println("i7==i8 " + (i7 == i8));// false
		System.out.println("i9==i10 " + (i9 == i10));// false***
		System.out.println("i11==i12+i13  " + (i11 == i12 + i13));// true***
		System.out.println("i14==i15+i16  " + (i14 == i15 + i16));// true***
		System.out.println("i540==i541 "+(i540==i541));//true
		Integer I540 = 540;//不在[-128-127]
		Integer I541 = 540;//不在[-128-127]
		System.out.println("I540==I541 "+(I540==I541));//false
		/**
		 * 1.i和i0均是普通类型(int)的变量，所以数据直接存储在栈中，而栈有一个很重要的特性：栈中的数据可以共享。当我们定义了int i =
		 * 40;，再定义int i0 = 40; 这时候会自动检查栈中是否有40这个数据，如果有，i0会直接指向i的40，不会再添加一个新的40。
		 * 2.i1和i2均是引用类型，在栈中存储指针，因为Integer是包装类。由于Integer
		 * 包装类实现了常量池技术，因此i1、i2的40均是从常量池中获取的，均指向同一个地址， 因此i1=12。
		 * 3.很明显这是一个加法运算，Java的数学运算都是在栈中进行的
		 * ，Java会自动对i1、i2进行拆箱操作转化成整型，因此i1在数值上等于i2+i3。 4.i4和i5
		 * 均是引用类型，在栈中存储指针，因为Integer是包装类
		 * 。但是由于他们各自都是new出来的，因此不再从常量池寻找数据，而是从堆中各自new一个对象，
		 * 然后各自保存指向对象的指针，所以i4和i5不相等，因为他们所存指针不同，所指向对象不同。 5.这也是一个加法运算，和3同理。
		 * 6.d1和d2均是引用类型
		 * ，在栈中存储指针，因为Double是包装类。但Double包装类没有实现常量池技术，因此Doubled1=1.0; 相当于Double
		 * d1=new Double(1.0);，是从堆new一个对象，d2同理。因此d1和d2存放的指针不同，指向的对象不同，所以不相等。
		 * 
		 * result:
		 *  i=i0	true
			i1=i2	true
			i1=i2+i3	true
			i4=i5	false
			i4=i5+i6	true
			i7==i8 false
			i9==i10 false
			i11==i12+i13  true
			i14==i15+i16  true
			i540==i541 true
			I540==I541 false
		 * 
		 */
	}
}
