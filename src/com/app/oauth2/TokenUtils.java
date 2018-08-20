package com.app.oauth2;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.SystemOutLogger;

import com.cyb.url.fina.FinalHttpClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月2日
 */
public class TokenUtils extends OauthBase{
	Log log = LogFactory.getLog(TokenUtils.class);
	public static void main(String[] args) {
		String token = "74bed745-28d7-49bf-b577-912dfc689922";
		String refreshToken = "95a3796c-cc2d-48a6-ad19-37fd652c74ce";
		checkToken(token);
		refreshToken(refreshToken);
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 根据refeshToken刷新token值<br>
	 *创建时间: 2017年7月15日
	 *@param refreshToken
	 */
	static void refreshToken(String refreshToken) {
		Map<String,String> para = new HashMap<>();
		para.put("client_id",clientId );
		para.put("client_secret", clientSecret);
		para.put("grant_type", "refresh_token");
		para.put("refresh_token", refreshToken);
		String result = FinalHttpClient.doPost(server+"oauth/token", para);
		System.out.println("刷新token结果："+result);
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 检查token的有效性<br>
	 *创建时间: 2017年7月15日
	 *@param token
	 */
	static void checkToken(String token){
		String rs = FinalHttpClient.doGet(server+"oauth/check_token?token="+token);
		System.out.println("token检查结果："+rs);
	}
}
