package com.cyb.diffcult.structurecontrol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月30日
 */
public class IF {
	static Log log = LogFactory.getLog(IF.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println(getTrue("left")==getFalse("right"));
		if(getTrue("left")||getFalse("right")){}//left
		System.out.println();
		if(getFalse("left")||getTrue("right")){}//left+right
		System.out.println();
		int a=6,b=8;
		boolean c = false;
		//c = 6<8||a++==b--;
		 c = a++==b--||6<8;//从左到右解析
		System.out.println(c+","+a+","+b);
		//从左到右解析，短路判断
		c=getFalse("l1")||getFalse("l2")||getTrue("r2")||getFalse("r1");
		System.out.println();
		boolean d = (getTrue("1") == getFalse("2") )? getTrue("3") : getFalse("4");
		//1,2,4
	}
	public static  boolean getTrue(String str){
		System.out.print(str+",");
		return true;
	}
	public static boolean getFalse(String str){
		System.out.print(str+",");
		return false;
	}
	
}
