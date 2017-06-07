package com.cyb.indiator;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.shucunwang.com/
 * @author DHUser
 *
 */
public class 阿拉伯数字4大写转换2 {
	
	private static final String[] NUMBERS = { "零", "一", "二", "三", "四", "五",
		"六", "七", "八", "九" };
	private static final String[] IUNITG = { "元","万","亿", "万"};
	private static final String[] IUNIT = { "", "十", "百","千"};
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
		/*number = "1234,1004,1007,1008";
		System.out.println(convert(number));
		number = "123,1004,1007,1008";
		System.out.println(convert(number));
		number = "12,1004,1007,1008";
		System.out.println(convert(number));*/
		number = "1,1004,1007,1008";
		System.out.println(convert(number)+groups);
		number = "1,0000,0000,0000";
		System.out.println(convert(number)+groups);
		number = "1,0000,0001,0000";
		System.out.println(convert(number)+groups);
		number = "1,0001,0000,0000";
		System.out.println(convert(number)+groups);
		number = "1,0000,0000,0001";
		System.out.println(convert(number)+groups);
		number = "4303020";
		System.out.println(convert(number)+groups);
		/*System.out.println(convert(number));
		number = "100,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));
		number = "10,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));
		number = "1,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));
		number = "10,000,303,020";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));
		number = "1004302";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));
		
		number = "10";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));
		number = "100000000000000";
		System.out.println(number + " " + 阿拉伯数字4大写转换2.convert(number));*/
		
	}
	public static List<String> groups = new ArrayList<String>();
	public static String convert(String number){
		number = number.replaceAll(",", "");// 去掉","
		StringBuffer sb = new StringBuffer();
		int len = number.length();
		groups = new ArrayList<String>();
		int group = len/4;
		int outOfGroup = len%4; 
		if(outOfGroup!=0){//有不满4位的一组
			group = group+1;
		}
		//i代表第几组i=0时
		/*for(int i=0;i<group;i++){
			if(i==0){
				sb.append(calQIAN(number.substring(0, outOfGroup)));
			}else{
				sb.append(calQIAN(number.substring(outOfGroup+(i-1)*4,outOfGroup+i*4)));
			}
		}*/
		String tmp = "";
		for(int i=0;i<group;i++){
			if(i==0){
				tmp = calQIAN(number.substring(0, outOfGroup));
				groups.add(tmp);
				if(tmp.equals(NUMBERS[0])){
					sb.append(NUMBERS[0]+IUNITG[group-i-1]);
				}else{
					sb.append(tmp+IUNITG[group-i-1]);//IUNITG[group-i]
				}
			}else{
				tmp = calQIAN(number.substring(outOfGroup+(i-1)*4,outOfGroup+i*4));
				groups.add(tmp);
				if(tmp.equals(NUMBERS[0])){
					sb.append(NUMBERS[0]+IUNITG[group-i-1]);
				}else{
					sb.append(tmp+IUNITG[group-i-1]);//IUNITG[group-i]
				}
			}
		}
		return sb.toString().replaceAll("零亿", "亿").replace("零万", "").replace("零元", "");
	}
	public static String calQIAN(String number){
		int[] integers = toArray(number);
		if(Long.valueOf(number)==0){
			return NUMBERS[0];
		}
		StringBuffer chineseInteger = new StringBuffer("");
		int length = integers.length;
		String key="";
		for (int i = 0; i < length; i++) {
			//倒数第二位补零，其他位不管
			if ((length - i - 1) > 0 && integers[i + 1] != 0){
				key += NUMBERS[0];
			}
			//指定的数字大写+数量级名
			chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
			key="";
		}
		return chineseInteger.toString();
	}
	
}
