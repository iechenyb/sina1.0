package com.app.oauth2;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.url.fina.FinalHttpClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月2日
 */
public class ImplicitGrantUtils extends OauthBase{
	Log log = LogFactory.getLog(ImplicitGrantUtils.class);
	static String url = "oauth/authorize?response_type=token&client_id=my-trusted-client&redirect_uri=http://baidu.com&state=123";
	public static void main(String[] args) {
		long exp = 1533197332l;
		System.out.println(exp-Calendar.getInstance().getTimeInMillis()/1000);
		System.out.println(exp-System.currentTimeMillis()/1000);
		System.out.println(System.nanoTime());
		FinalHttpClient.doGet(server+url);//需要登录
	}
}
