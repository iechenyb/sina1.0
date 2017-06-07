package com.cyb.indiator;

import java.math.BigDecimal;

public class Number2Case {

	/** 大写数字 */
	private static final String[] NUMBERS_ = { "零", "壹", "贰", "叁", "肆", "伍",
			"陆", "柒", "捌", "玖" };
	private static final int DFT_SCALE = 2;
	public static void main(String[] args) {
		System.out.println(NumberToChinese("1004302"));
		System.out.println(NumberToChinese("1000000"));
	}
	public static String NumberToChinese(String input){   
        //String s1="零壹贰叁肆伍陆柒捌玖";  
        String s1="零一二三四五六七八九";   
        String s4="分角整元拾佰仟万拾佰仟亿拾佰仟";   
        String temp="";   
        String result="";   
        if (input==null) return "输入的字串不是数字串只能包括以下字符（'0'~'9','.'),输入字串最大只能精确到仟亿，小数点只能两位！";   
        temp=input.trim();   
        float f;   
        try{   
            f=Float.parseFloat(temp);   
        }catch(Exception e){   
            return "输入的字串不是数字串只能包括以下字符（'0'~'9','.'),输入字串最大只能精确到仟亿，小数点只能两位！";   
        }   
        int len=0;   
        if(temp.indexOf(".")==-1) 
        	len=temp.length();   
        else 
        	len=temp.indexOf(".");   
        if(len>s4.length()-3) 
        	return("输入字串最大只能精确到仟亿，小数点只能两位！");   
        int n1=0;   
        String num="";   
        String unit="";   
        for(int i=0;i<temp.length();i++){   
            if(i>len+2){break;}   
            if(i==len) {continue;}   
            n1=Integer.parseInt(String.valueOf(temp.charAt(i)));   
            num=s1.substring(n1,n1+1);   
            n1=len-i+2;   
            unit=s4.substring(n1,n1+1);   
            result=result.concat(num).concat(unit);   
        }   
        if((len==temp.length())||(len==temp.length()-1)) result=result.concat("整");   
        if(len==temp.length()-2) result=result.concat("零分");   
        return result;   
    }   
	/**
	* BigDecimal 相乘,四舍五入保留0位
	* 
	* @param a
	* @param b
	* @return a*b
	*/
	public static BigDecimal mutiply(String a, String b, int roundingMode) {
		BigDecimal bd = new BigDecimal(a);
		return bd.multiply(new BigDecimal(b)).setScale(DFT_SCALE, roundingMode);
	}

	/**
	* BigDecimal 相除,四舍五入保留两位
	* 
	* @param a
	* @param b
	* @return a/b
	*/
	public static BigDecimal div(String a, String b, int roundingMode) {
		BigDecimal decimal1 = new BigDecimal(a);
		BigDecimal decimal2 = new BigDecimal(b);
		return decimal1.divide(decimal2, DFT_SCALE, roundingMode);
	}

	/**
	* BigDecimal 相加,四舍五入保留两位
	* 
	* @param a
	* @param b
	* @return a+b
	*/
	public static BigDecimal sum(String a, String b, int roundingMode) {
		BigDecimal decimal1 = new BigDecimal(a);
		BigDecimal decimal2 = new BigDecimal(b);
		// DecimalFormat format = new DecimalFormat("#0.00");
		return decimal1.add(decimal2).setScale(DFT_SCALE, roundingMode);
	}

	/**
	* BigDecimal 相减,四舍五入保留两位
	* 
	* @param a
	* @param b
	* @return a+b
	*/
	public static BigDecimal sub(String a, String b, int roundingMode) {
		BigDecimal decimal1 = new BigDecimal(a);
		BigDecimal decimal2 = new BigDecimal(b);
		// DecimalFormat format = new DecimalFormat("#0.00");
		return decimal1.subtract(decimal2).setScale(DFT_SCALE, roundingMode);
	}
	/**
	* 100.00 为10000
	* 
	* @param a
	* @return
	*/
	public static BigDecimal format(String a, int roundingMode) {
		return new BigDecimal(a).multiply(new BigDecimal(100)).setScale(0,
				roundingMode);
	}

}
