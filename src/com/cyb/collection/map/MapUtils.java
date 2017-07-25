package com.cyb.collection.map;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月24日
 */
public class MapUtils {
	static Log log = LogFactory.getLog(MapUtils.class);
	public static void showMap(Map<Object,Object> data){
		Iterator<Object> it = data.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			log.info(key+"="+data.get(key)+",");
		}
	}
}
