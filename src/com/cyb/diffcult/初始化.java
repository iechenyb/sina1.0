package com.cyb.diffcult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年8月22日
 */
public class 初始化 {
	static Boolean[] B = new Boolean[10];
	static boolean[] b = new boolean[10];
	static Integer[] Ia = new Integer[10];
	static int[] ia = new int[10];
	static Log log = LogFactory.getLog(初始化.class);
    static boolean bool;
	public static void main(String[] args) {
		log.info(Math.floor(-5.5));//向下取整
		log.info(Math.floor(5.5));//向下取整
		boolean bool1;//必须初始化在方法内部定义的局部变量，成语变量会默认初始化。
		//System.out.println("boolean default:"+bool+","+bool1);//编译不通过
		for(int i=0;i<b.length;i++){
		log.info(b[i]);
		}
		for(int i=0;i<Ia.length;i++){
			log.info(Ia[i]);
		}
		for(int i=0;i<ia.length;i++){
			log.info(ia[i]);
		}
		int h[] = new int[10];
		//int i[10] = new int[];//错误
		//int j[10] = new int[10];
		//int [][]a =new int[][10];  //错误
		//int b[10][10]=new int[][];//错误
		//int c[10][10]=new int[10][10]; //错误 
		int [][] d= {null}; 
		//int [][] e=new int{{1,1},{1,1,1},{}};//错误
		int [][] f=new int[10][]; 
		//int [][] f=new int[10][10]; 
		//int [10][] x=new int[][];//错误
		//int [][10] y=new int[][]; //错误
		//int [10][10] z=new int[10][10]; //错误
		int []g[] = new int[][]{}; 
		int i = 0xFFFFFFFF;
		char c = '\u0571';
		byte b = 01;
		int ia = 'a';
		long l = 455566666L;
		int j=0xFFFFFFF1;
		int i1=~j; 
		log.info(~1);//-2
	}
}
