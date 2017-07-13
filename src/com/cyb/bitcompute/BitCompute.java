package com.cyb.bitcompute;

import java.util.Random;

public class BitCompute {
	 public static void main(String[] argv) {
	        testRadix();
	        testBitMove();
	    }

	    /**
	     * 测试各种进制之间的转换
	     */
	    public static void testRadix() {
	        // 二进制没有特殊表达方法，可用字符串表示
	        String i2str = "1000";//二进制表示数字8
	        // 八进制前面加0
	        int i8 = 0100;//8进制表示8
	        // 十进制是自然的
	        int i10 = 8;//10进制表示8
	        // 十六进制前面加0x
	        int i16 = 0x8;//16进制表示8

	        // 将二进制的字符串转换为int
	        int i2 = Integer.parseInt(i2str, 2);

	        // 测试证明i2、i8、i10和i16本质上是相等的，只是表达形式不同
	        if (i2 == i8) {
	            System.out.println("i2==i8");
	        }
	        if (i8 == i10) {
	            System.out.println("i8==i10");
	        }
	        if (i10 == i16) {
	            System.out.println("i10==i16");
	        }
	        int i = i2;
	        // 转换为各种形式的字符串
	        System.out.println("radix=2,i=" + Integer.toBinaryString(i));
	        System.out.println("radix=8,i=" + Integer.toOctalString(i));
	        System.out.println("radix=10,i=" + Integer.toString(i));
	        System.out.println("radix=16,i=" + Integer.toHexString(i));
	        // 甚至可以有7进制、5进制
	        System.out.println("radix=7,i=" + Integer.toString(i, 7));
	        System.out.println("radix=5,i=" + Integer.toString(i, 5));

	        // 由此可知，在内存中i、i2、i8、i10、i16都是一样的，不同的只是表现形式；因此不同进制之间不需要转换，只是在需要时用不同的形式表达
	        int j8 = 010;
	        int j10 = j8;
	        System.out.println("j8=" + j8 + ",j10=" + j10);
	        int j16 = 0x10;
	        j10 = j16;
	        System.out.println("j16=" + j16 + ",j10=" + j10);
	        System.out.println(Integer.toBinaryString(i2)+",i2右移1位"+Integer.toBinaryString((i2>>1))+","+(i2>>1));
	        //移位 M mod 32 = N ,实际上移动N位
	        System.out.println(Integer.toBinaryString(i2)+",i2左移1位"+Integer.toBinaryString((i2<<1))+","+(i2<<1));
	        System.out.println(Integer.toBinaryString(i8)+",i8右移1位"+Integer.toBinaryString((i8>>1))+","+(i8>>1));
	        System.out.println(Integer.toBinaryString(i8)+",i8左移1位"+Integer.toBinaryString((i8<<1))+","+(i8<<1));
	        System.out.println(Integer.toBinaryString(i16)+",i16右移1位"+Integer.toBinaryString((i16>>1))+","+(i16>>1));
	        System.out.println(Integer.toBinaryString(i16)+",i16左移1位"+Integer.toBinaryString((i16<<1))+","+(i16<<1));
	    }

	    /**
	     * 测试位移运算，经常用于计算Color
	     */
	    public static void testBitMove() {
	        Random rand = new Random();
	        int red = rand.nextInt(255);
	        int green = rand.nextInt(255);
	        int blue = rand.nextInt(255);
	        int color = 0xFF000000;
	        color = color | red << 16 | green << 8 | blue;
	        System.out.println("red = " + Integer.toHexString(red) + ", green = "
	                + Integer.toHexString(green) + ", blue = "
	                + Integer.toHexString(blue));
	        System.out.println("color in Hex = " + Integer.toHexString(color));
	    }
	}