package com.cyb.redis.qutoes;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.cyb.date.DateUtil;
import com.cyb.redis.RedisClient;

public class OneMinuteQutoesUtils {
	static String RealPrix="Qutoes:Minute:One";
	public static void main(String[] args) {
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		String2Object("","",jedis);
	}
	static Log log = LogFactory.getLog(OneMinuteQutoesUtils.class);
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 最新实时行情更新<br>
	 *创建时间: 2017年7月15日hj12
	 *@param code
	 *@param msg
	 *@param jedis
	 */
	public static void String2Object(String code,String msg,Jedis jedis){	
		long s = System.currentTimeMillis();
		String qutoes ="";
		try {
			String[] dataArr = msg.replaceAll("\"", "").replaceAll(";", "").split(",");
			jedis.hset(RealPrix+code, "code", code);
			jedis.hset(RealPrix+code, "name",dataArr[Contants.NAME] );
			jedis.hset(RealPrix+code, "open",dataArr[Contants.OPEN] );
			jedis.hset(RealPrix+code, "preclose",dataArr[Contants.PRECLOSE] );
			jedis.hset(RealPrix+code, "high",dataArr[Contants.HIGH] );
			jedis.hset(RealPrix+code, "low",dataArr[Contants.LOW] );
			jedis.hset(RealPrix+code, "price",dataArr[Contants.PRICE] );
			jedis.hset(RealPrix+code, "close",dataArr[Contants.PRICE] );
			jedis.hset(RealPrix+code, "day",dataArr[Contants.DAY] );
			jedis.hset(RealPrix+code, "time",dataArr[Contants.TIME] );
			jedis.hset(RealPrix+code, "columcash",dataArr[Contants.COLUMNCASH] );
			jedis.hset(RealPrix+code, "turnvolume",dataArr[Contants.TURNVOLUME] );
			jedis.hset(RealPrix+code, "systime",DateUtil.timeToMilis(new Date()) );
		} catch (Exception e) {
			log.error(qutoes+"->"+e.toString());
		}
		long e = System.currentTimeMillis();
		log.info("处理记录数="+((e-s)/1000)+"秒"+(e-s)%1000+"毫秒");
	}
	
	public static void String2ObjectHK(String code,String msg,Jedis jedis ){		
		long s = System.currentTimeMillis();
		String qutoes ="";
		try {
			String[] dataArr = msg.replaceAll("\"", "").replaceAll(";", "").split(",");
			jedis.hset(RealPrix+code, "code", code);
			jedis.hset(RealPrix+code, "name",dataArr[Contants.ZNNAME_] );
			jedis.hset(RealPrix+code, "open",dataArr[Contants.OPEN_] );
			jedis.hset(RealPrix+code, "preclose",dataArr[Contants.PRECLOSE_] );
			jedis.hset(RealPrix+code, "high",dataArr[Contants.HIGH_] );
			jedis.hset(RealPrix+code, "low",dataArr[Contants.LOW_] );
			jedis.hset(RealPrix+code, "price",dataArr[Contants.PRICE_] );
			jedis.hset(RealPrix+code, "close",dataArr[Contants.PRICE_] );
			jedis.hset(RealPrix+code, "day",dataArr[Contants.DAY_] );
			jedis.hset(RealPrix+code, "time",dataArr[Contants.TIME_] );
			jedis.hset(RealPrix+code, "columcash",dataArr[Contants.CJE] );
			jedis.hset(RealPrix+code, "turnvolume",dataArr[Contants.CJL] );
			jedis.hset(RealPrix+code, "systime",DateUtil.timeToMilis(new Date()) );
		} catch (Exception e) {
			log.error(qutoes+"->"+e.toString());
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		log.info("处理记录数="+((e-s)/1000)+"秒"+(e-s)%1000+"毫秒");
	}
}
