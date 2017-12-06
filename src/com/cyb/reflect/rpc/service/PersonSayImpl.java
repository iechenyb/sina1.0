package com.cyb.reflect.rpc.service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月6日
 */
public class PersonSayImpl implements SayService{
	Log log = LogFactory.getLog(PersonSayImpl.class);

	@Override
	public String say(String content) {
		log.info("sameone say is "+content);
		return content;
	}

	@Override
	public String say(String name, String content) {
		log.info("sameone say is "+content+" to "+name);
		return name+","+content;
	}
}
