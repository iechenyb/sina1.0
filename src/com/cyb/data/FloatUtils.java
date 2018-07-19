package com.cyb.data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年7月6日
 */
public class FloatUtils {
	Log log = LogFactory.getLog(FloatUtils.class);
	static final float s1 = 1000.1f/0.0f;
	static final float s2 = 0.0f/0.0f;
	static final float s3 = 0.8f/0.0f;
	static final float s4 = -0.8f/0.0f;
	public static final float NaN = 0.0f / 0.0f;
	public static void main(String[] args) {
		System.out.println(s2 == s2);//false nan和nan不相等
		System.out.println(s1 == s1);//true *
		System.out.println(s1 > 0);//true *
		System.out.println(s3 == s1);//true
		System.out.println(s4 == s1);//false
		System.out.println(s1);//Infinity 正无穷大
		System.out.println(s2);//NaN
		System.out.println(s3);//Infinity 正无穷大
		System.out.println(s4);//-Infinity 负无穷大
		System.out.println(Float.isNaN(s1));//不是NaN
		System.out.println(Float.isInfinite(s1));//是无穷
	}

}
