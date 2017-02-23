package com.cyb.charset;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlCodeUtils {
@SuppressWarnings("deprecation")
public static void main(String[] args) {
	String str="\u63a5\u53e3\u5df2\u7ecf\u505c\u7528";
	System.out.println(URLEncoder.encode("北京"));
	System.out.println(URLDecoder.decode(str));
}
}
