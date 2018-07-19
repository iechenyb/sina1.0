package com.cyb.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author DHUser
 *
 */
public class DateSafeUtil {
	private static ThreadLocal<DateFormat> formattor = new ThreadLocal<DateFormat>();
	private static ThreadLocal<Calendar> calendarThreadLocal = new ThreadLocal<Calendar>();
	public static String format(Date date,String model) throws ParseException{
		formattor.set(new SimpleDateFormat(model));
		String res = formattor.get().format(date);
		formattor.remove();
		return res;
	}
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日
	 *@param date
	 *@return
	 */
	public static String timeToMilis(Date date) {
		formattor.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
		String dateString = formattor.get().format(date);
		formattor.remove();
		return dateString;
	}
	public static String timeToMilis() {
		return timeToMilis(new Date());
	}
	public static String timeToSec(Date date) {
		formattor.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String dateString = formattor.get().format(date);
		formattor.remove();
		return dateString;
	}
	public static String timeToSec() {
		return timeToSec(new Date());
	}
	public static String descTimeToSec(Date date) {
		formattor.set(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));
		String dateString = formattor.get().format(date);
		formattor.remove();
		return dateString;
	}
	public static String descTimeToSec(){
		return descTimeToSec(new Date());
	}
	
	public static Long date2long14(Date date) {
		formattor.set(new SimpleDateFormat("yyyyMMddHHmmss"));
		String dateString = formattor.get().format(date);
		Long time =  Long.valueOf(dateString);
		formattor.remove();
		return time;
	}
	public static Long date2long14(){
		return date2long14(new Date());
	}

	public static Calendar calendar(String yyyymmddhhmmss) {
		calendarThreadLocal.set(Calendar.getInstance());
		Calendar cal = calendarThreadLocal.get();
		try{
			yyyymmddhhmmss = yyyymmddhhmmss.replaceAll("-", "").replaceAll(":", "").replaceAll("\\\\", "").replaceAll(" ", "")
					.replace("/", "");
			if (yyyymmddhhmmss.length() != 8 && yyyymmddhhmmss.length() != 14) {
				try {
					throw new Exception("参数必须为8位或者12位数字");
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
			if (yyyymmddhhmmss.length() == 8) {
				yyyymmddhhmmss = yyyymmddhhmmss + "000000";
			}
			int year = Integer.valueOf(yyyymmddhhmmss.substring(0, 4));
			int month = Integer.valueOf(yyyymmddhhmmss.substring(4, 6));
			int day = Integer.valueOf(yyyymmddhhmmss.substring(6, 8));
			int hour = Integer.valueOf(yyyymmddhhmmss.substring(8, 10));
			int min = Integer.valueOf(yyyymmddhhmmss.substring(10, 12));
			int sec = Integer.valueOf(yyyymmddhhmmss.substring(12, 14));
			cal.set(year, month - 1, day, hour, min, sec);
		}finally{
			calendarThreadLocal.remove();
		}
		return cal;
	}

	public static Calendar calendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(format(new Date(),"yyyMMddHHmmss"));
	}
}
