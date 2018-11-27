package com.cyb.json;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年11月26日
 */
public class EmpValue {
	Log log = LogFactory.getLog(EmpValue.class);
	public static void main(String[] args) {
		/*
		 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
		 * WriteMapNullValue——–是否输出值为null的字段,默认为false
		 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
		 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
		 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为"",而非null
		 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
		 */
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("a", 1);
		jsonMap.put("b", "");
		jsonMap.put("c", null);
		jsonMap.put("d", "json");
 
		String str = JSONObject.toJSONString(jsonMap);
		// ①忽略null输出
		System.out.println("str:"+str);
 
		String str2 = JSONObject.toJSONString(jsonMap,
				SerializerFeature.WriteMapNullValue);//
		// ②
		System.out.println("str2:"+str2);
 
		String json = "{\"fail\":null,\"updateTimestamp\":\"1484096131863\",\"productName\":\"json测试\"}";
		// ③忽略null输出
		System.out.println(JSON.parse(json));
		// ④
		System.out.println(JSONObject.toJSON(json));
	}
}
