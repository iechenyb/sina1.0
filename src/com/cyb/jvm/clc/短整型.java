package com.cyb.jvm.clc;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class 短整型 {
	Log log = LogFactory.getLog(短整型.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		short s40 = 40;
		short s41 = 40;
		System.out.println("b40=b41\t" + (s40 == s41));//true
		short s127 = 127;//最大值
		short s127_ = 127;//最大值
		
		short s128 = 128;//
		short s128_= 128;//最小值
		System.out.println("s128=s128_\t" + (s128 == s128_));//true
		short s129=-129;
		short s129_=-129;
		System.out.println("s129=s129_\t" + (s129 == s129_));//true
		
		Short S40=Short.valueOf(s40);
		Short S41=Short.valueOf(s41);
		System.out.println("S40=S41\t" + (S40 == S41));//true
		
		Short S200=Short.valueOf("200");
		Short S201=Short.valueOf("200");
		System.out.println("S200=S201\t" + (S200 == S201));//false

		Short S127=127;
		Short S127_=127;
		System.out.println("S129=S129\t" + (S127 == S127_));//true max
		
		Short S128=-128;
		Short S128_=-128;
		System.out.println("S128=S128\t" + (S128_ == S128));//true min
		
		/**
		 *  b40=b41	true
			s128=s128_	true
			s129=s129_	true
			S40=S41	true
			S200=S201	false
			S129=S129	true
			S128=S128	true
		 */
	}
}
