package com.cyb.indiator;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.shucunwang.com/
 * @author DHUser
 *
 */
public class 阿拉伯数字4大写转换1 {
	
	private static final String[] NUMBERS = { "零", "一", "二", "三", "四", "五",
		"六", "七", "八", "九" };
	private static final String[] IUNIT4 = { "", "十", "百", "千"};
	/** 小数部分的单位 */
	private static final String[] DUNIT = { "角", "分", "厘" };
	/**
	* 整数部分和小数部分转换为数组，从高位至低位
	*/
	private static int[] toArray(String number) {
		int[] array = new int[number.length()];
		for (int i = 0; i < number.length(); i++) {
			array[i] = Integer.parseInt(number.substring(i, i + 1));
		}
		return array;
	}
	public static void main(String[] args) {
		String number = "10345";//万
		number = "1005100410071008";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		System.out.println(convert(number));
		number = "100,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "10,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "1,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "10,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "1004302";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "4303020";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "10";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		number = "100000000000000";
		System.out.println(number + " " + 阿拉伯数字4大写转换1.convert(number));
		
	}
	
	public static String convert(String number){
		number = number.replaceAll(",", "");// 去掉","
		StringBuffer sb = new StringBuffer();
		List<String> groups = new ArrayList<String>();
		int len = number.length();
		if(len>12){
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(0*4,len-3*4))+"万");
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(len-3*4,len-2*4))+"亿");
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(len-2*4,len-1*4))+"万");
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(len-1*4,len-0*4)));
			groups.add(number.substring(0,len-12));
			groups.add(number.substring(len-12,len-8));
			groups.add(number.substring(len-8,len-4));
			groups.add(number.substring(len-4,len));
		}else if(len>8){
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(0,len-8))+"亿");
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(len-8,len-4))+"万");
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(len-4,len)));
			groups.add(number.substring(0,len-8));
			groups.add(number.substring(len-8,len-4));
			groups.add(number.substring(len-4,len));
		}else if(len>4){
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(0,len-4))+"万");
			sb.append(阿拉伯数字4大写转换1.calQIAN(number.substring(len-4,len)));
			groups.add(number.substring(0,len-4));
			groups.add(number.substring(len-4,len));
		}else{
			groups.add(number);
			sb.append(阿拉伯数字4大写转换1.calQIAN(number));
		}
		return sb.toString();
	}
	public static String calQIAN(String number){
		int[] integers = toArray(number);
		StringBuffer chineseInteger = new StringBuffer("");
		int length = integers.length;
		String key="";
		for (int i = 0; i < length; i++) {
			//倒数第二位补零，其他位不管
			if ((length - i - 1) > 0 && integers[i + 1] != 0){
				key += NUMBERS[0];
			}
			//指定的数字大写+数量级名
			chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT4[length - i - 1]));
			key="";
		}
		return chineseInteger.toString();
	}
	
}
