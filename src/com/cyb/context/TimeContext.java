package com.cyb.context;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年11月27日
 */
public class TimeContext {
	Log log = LogFactory.getLog(TimeContext.class);
	static ThreadLocal<Long> start = new ThreadLocal<>();
	static SimpleDateFormat formatter = new SimpleDateFormat("mm分ss秒SSS毫秒",Locale.ENGLISH);//初始化Formatter的转换格式。
	public static  String time(long millis){
		formatter.setTimeZone(TimeZone.getTimeZone("GMT_ID"));
		String hms = formatter.format(millis);
		return hms;
	}
	public static void recordTimeStart(){
		start.set(System.currentTimeMillis());
	}
	
	public static long calExecuteTime(){
		long millis = System.currentTimeMillis() -  start.get();
		System.out.println("用时("+millis+"毫秒)："+time(millis));
		return millis;
	}
	
	public static void main(String[] args) {
		long  ms = 300 * 1000+20*1000 + 100;//毫秒数
		formatter.setTimeZone(TimeZone.getTimeZone("GMT_ID"));
		String hms = formatter.format(ms);
		System.out.println(hms);
	}
}
