package com.cyb.diffcult.反斜杠;
import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *http://blog.csdn.net/chindroid/article/details/7735832
 *http://blog.csdn.net/u011192409/article/details/46815895
 *创建时间: 2018年1月18日
 */
public class 反斜杠使用 {
	Log log = LogFactory.getLog(反斜杠使用.class);
	public static void main(String[] args) {
		String filePath = "d:/data/tmp/aa.txt";
		filePath = "d:\\data\\tmp\\aa.txt";// \是转义字符 如\n \t
		System.out.println(File.pathSeparator);
		System.out.println(File.pathSeparatorChar);
		System.out.println(File.separator);//windows  \  linux /
		System.out.println(File.separatorChar);//windows  \  linux /
		//System.out.println(File.createTempFile("xx", "yy"));
		//System.out.println(File.createTempFile("xx", "zz", "d:/data/tmp"));
		System.out.println(filePath);//d:\data\tmp\aa.txt
		System.out.println("123.456.789".indexOf("."));//返回第一个.的索引
		System.out.println("123.456.789".lastIndexOf("."));//返回最后一个.的索引
	}
}
