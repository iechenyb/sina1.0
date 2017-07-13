package com.cyb.bitcompute;

import java.util.Random;

public class BitCompute {
	 public static void main(String[] argv) {
	        testRadix();
	        testBitMove();
	    }

	    /**
	     * ���Ը��ֽ���֮���ת��
	     */
	    public static void testRadix() {
	        // ������û�������﷽���������ַ�����ʾ
	        String i2str = "1000";//�����Ʊ�ʾ����8
	        // �˽���ǰ���0
	        int i8 = 0100;//8���Ʊ�ʾ8
	        // ʮ��������Ȼ��
	        int i10 = 8;//10���Ʊ�ʾ8
	        // ʮ������ǰ���0x
	        int i16 = 0x8;//16���Ʊ�ʾ8

	        // �������Ƶ��ַ���ת��Ϊint
	        int i2 = Integer.parseInt(i2str, 2);

	        // ����֤��i2��i8��i10��i16����������ȵģ�ֻ�Ǳ����ʽ��ͬ
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
	        // ת��Ϊ������ʽ���ַ���
	        System.out.println("radix=2,i=" + Integer.toBinaryString(i));
	        System.out.println("radix=8,i=" + Integer.toOctalString(i));
	        System.out.println("radix=10,i=" + Integer.toString(i));
	        System.out.println("radix=16,i=" + Integer.toHexString(i));
	        // ����������7���ơ�5����
	        System.out.println("radix=7,i=" + Integer.toString(i, 7));
	        System.out.println("radix=5,i=" + Integer.toString(i, 5));

	        // �ɴ˿�֪�����ڴ���i��i2��i8��i10��i16����һ���ģ���ͬ��ֻ�Ǳ�����ʽ����˲�ͬ����֮�䲻��Ҫת����ֻ������Ҫʱ�ò�ͬ����ʽ���
	        int j8 = 010;
	        int j10 = j8;
	        System.out.println("j8=" + j8 + ",j10=" + j10);
	        int j16 = 0x10;
	        j10 = j16;
	        System.out.println("j16=" + j16 + ",j10=" + j10);
	        System.out.println(Integer.toBinaryString(i2)+",i2����1λ"+Integer.toBinaryString((i2>>1))+","+(i2>>1));
	        //��λ M mod 32 = N ,ʵ�����ƶ�Nλ
	        System.out.println(Integer.toBinaryString(i2)+",i2����1λ"+Integer.toBinaryString((i2<<1))+","+(i2<<1));
	        System.out.println(Integer.toBinaryString(i8)+",i8����1λ"+Integer.toBinaryString((i8>>1))+","+(i8>>1));
	        System.out.println(Integer.toBinaryString(i8)+",i8����1λ"+Integer.toBinaryString((i8<<1))+","+(i8<<1));
	        System.out.println(Integer.toBinaryString(i16)+",i16����1λ"+Integer.toBinaryString((i16>>1))+","+(i16>>1));
	        System.out.println(Integer.toBinaryString(i16)+",i16����1λ"+Integer.toBinaryString((i16<<1))+","+(i16<<1));
	    }

	    /**
	     * ����λ�����㣬�������ڼ���Color
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