package com.cyb.base64;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Utils {
	/*** 
     * encode by Base64 
     */  
    public static String encodeBase64(byte[]input) throws Exception{  
        Class<?> clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, new Object[]{input});  
         return (String)retObj;  
    }  
    /*** 
     * decode by Base64 
     */  
    public static byte[] decodeBase64(String input) throws Exception{  
        Class<?> clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
         Object retObj=mainMethod.invoke(null, input);  
         return (byte[])retObj;  
    } 
    /** 
     * @param bytes 
     * @return 
     */  
    public static byte[] decode1(final byte[] bytes) {  
        return Base64.decodeBase64(bytes);  
    }  
  
    /** 
     * 二进制数据编码为BASE64字符串 
     * 
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode1(final byte[] bytes) {  
        return new String(Base64.encodeBase64(bytes));  
    } 
    /**  
     * 编码  
     * @param bstr  
     * @return String  
     */    
    public static String encode(byte[] bstr){    
    return new sun.misc.BASE64Encoder().encode(bstr);    
    }    
    
    /**  
     * 解码  
     * @param str  
     * @return string  
     */    
    public static byte[] decode(String str){    
    byte[] bt = null;    
    try {    
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
        bt = decoder.decodeBuffer( str );    
    } catch (IOException e) {    
        e.printStackTrace();    
    }    
    
        return bt;    
    }   
    public static void main(String[] args) throws Exception {
    	String str = "client:m_volunteer";String target="Y2xpZW50Om1fdm9sdW50ZWVy";
		System.out.println(new String(encodeBase64(str.getBytes())).equals(target));
		System.out.println(new String(decodeBase64(target)));
		System.out.println(new String(encode1(str.getBytes())).equals(target));
		System.out.println(new String(decode1(target.getBytes())));
		System.out.println(new String(encode(str.getBytes())).equals(target));
		System.out.println(new String(decode(target)));
	}
}
