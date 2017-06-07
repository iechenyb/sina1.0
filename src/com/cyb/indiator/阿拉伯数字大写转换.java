package com.cyb.indiator;

/**
 * 
 * @author DHUser
 *
 */
public class 阿拉伯数字大写转换 {
	
	private static final String[] NUMBERS = { "零", "一", "二", "三", "四", "五",
		"六", "七", "八", "九" };
	/** 整数部分的单位 千万亿级别处理 */
	private static final String[] IUNIT = { "元", "十", "百", "千", "万", "十", "百",
			"千", "亿", "十", "百", "千", "万", "十", "百", "千" };//index[0-15]
	/** 小数部分的单位 */
	private static final String[] DUNIT = { "角", "分", "厘" };

	/**
	* 得到大写金额。
	*/
	public static String toChinese(String str) {
		str = str.replaceAll(",", "");// 去掉","
		String integerStr;// 整数部分数字
		String decimalStr;// 小数部分数字

		// 初始化：分离整数部分和小数部分
		if (str.indexOf(".") > 0) {
			integerStr = str.substring(0, str.indexOf("."));
			decimalStr = str.substring(str.indexOf(".") + 1);
		} else if (str.indexOf(".") == 0) {//没有整数位
			integerStr = "";
			decimalStr = str.substring(1);
		} else {//只有整数位
			integerStr = str;
			decimalStr = "";
		}
		// integerStr去掉首0，不必去掉decimalStr的尾0(超出部分舍去)
		if (!integerStr.equals("")) {
			integerStr = Long.toString(Long.parseLong(integerStr));
			if (integerStr.equals("0")) {
				integerStr = "";
			}
		}
		// overflow超出处理能力，直接返回
		if (integerStr.length() > IUNIT.length) {
			System.out.println(str + ":超出处理能力");
			return str;
		}

		int[] integers = toArray(integerStr);// 整数部分数字
		boolean isMust5 = isMust5(integerStr);// 设置万单位
		int[] decimals = toArray(decimalStr);// 小数部分数字
		return getChineseInteger(integers, isMust5)
				+ getChineseDecimal(decimals);
	}

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

	/**
	* 得到中文金额的整数部分。
	*/
	private static String getChineseInteger(int[] integers, boolean isMust5) {
		StringBuffer chineseInteger = new StringBuffer("");
		int length = integers.length;
		for (int i = 0; i < length; i++) {
			// 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
			// 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
			String key = "";
			if (integers[i] == 0) {//当前位置数字为0
				if ((length - i - 1) == 12)// 万(亿)(必填)
				{
					key = IUNIT[4];//万
			    }else if ((length - i - 1) == 8)// 亿(必填)
				{	
					key = IUNIT[8];//亿
				}else if ((length - i - 1) == 4 )// 万(不必填)
				{
					key = IUNIT[4];//万
				}else if ((length - i - 1) == 0)// 元(必填)
				{
					key = IUNIT[0];
				}
				/*0遇非0时补零，不包含最后一位且下一位也不等于0万为后不补充0
				(length - i - 1)==2 千后零
				(length - i - 1)==4 万后零 
				(length - i - 1)==6)亿后零				 
				*/
				if ((length - i - 1) > 0 && integers[i + 1] != 0 && ((length - i - 1)!=4)  && (length - i - 1)!=2222 && (length - i - 1)!=6)
					key += NUMBERS[0];
			}
			//指定的数字大写+数量级名
			chineseInteger.append(integers[i] == 0 ? key : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
		}
		return chineseInteger.toString();
	}

	/**
	* 得到中文金额的小数部分。
	*/
	private static String getChineseDecimal(int[] decimals) {
		StringBuffer chineseDecimal = new StringBuffer("");
		for (int i = 0; i < decimals.length; i++) {
			// 舍去3位小数之后的
			if (i == 3)
				break;
			chineseDecimal.append(decimals[i] == 0 ? ""
					: (NUMBERS[decimals[i]] + DUNIT[i]));
		}
		return chineseDecimal.toString();
	}

	/**
	* 判断第5位数字的单位"万"是否应加。
	*/
	private static boolean isMust5(String integerStr) {
		int length = integerStr.length();
		if (length > 4) {
			String subInteger = "";
			if (length > 8) { 
				// 取得从低位数，第5到第8位的字串
				subInteger = integerStr.substring(length - 8, length - 4);
			} else {
				subInteger = integerStr.substring(0, length - 4);
			}
			return Integer.parseInt(subInteger) > 0;
		} else {
			return false;
		}
	}

	
	public static void main(String[] args) {
		String number = "10345";//万
		/*System.out.println(number + " " + MoneyUtil.toChinese(number));
		number = "10000";//万
		System.out.println(number + " " + MoneyUtil.toChinese(number));
		number = "123456789";//亿
		System.out.println(number + " " + MoneyUtil.toChinese(number));
		number = "1234567891234";//万亿
		System.out.println(number + " " + MoneyUtil.toChinese(number));
		number = "5000002";*/
		number = "10,000,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "1,000,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "100,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "10,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "1,000,000,303,020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "10,000,303,020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "1004302";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "4303020";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "10";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "00001";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
		number = "1";
		System.out.println(number + " " + 阿拉伯数字大写转换.toChinese(number));
	}
}
