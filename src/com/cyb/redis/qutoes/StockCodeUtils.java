package com.cyb.redis.qutoes;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.app.stock.Stock;
import com.cyb.date.DateUtil;
import com.cyb.redis.RedisClient;
/**
 * 股票代码抓取工具
 * @author iechenyb
 *
 */
public class StockCodeUtils {
	//中文名  code exchang  usecode    Qutoes:sh:code:{}
	public static String CodePrix="Qutoes:Code:";
	public static void main(String[] args) {
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		saveCodeToRedis(null,jedis);
	}
	static Log log = LogFactory.getLog(StockCodeUtils.class);
	public static void saveCodeToRedis(Stock stock,Jedis jedis){	
		long s = System.currentTimeMillis();
		try {
			jedis.hset(CodePrix+stock.getExchange()+":"+stock.getCode(), "code", stock.getCode());
			jedis.hset(CodePrix+stock.getExchange()+":"+stock.getCode(), "name", stock.getName());
			jedis.hset(CodePrix+stock.getExchange()+":"+stock.getCode(), "exchange", stock.getExchange());
			jedis.hset(CodePrix+stock.getExchange()+":"+stock.getCode(), "systime",DateUtil.timeToMilis(new Date()) );
		} catch (Exception e) {
			log.error(e.toString());
		}
		long e = System.currentTimeMillis();
		log.info("处理记录数="+((e-s)/1000)+"秒"+(e-s)%1000+"毫秒");
	}
}
