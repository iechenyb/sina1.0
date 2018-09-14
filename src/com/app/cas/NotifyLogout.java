package com.app.cas;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年7月30日
 */
public class NotifyLogout {
	static Log log = LogFactory.getLog(NotifyLogout.class);
     /**
      * 
      *作者 : iechenyb<br>
      *方法描述: post<br>
      *创建时间: 2017年7月15日
      *@param url
      *@param message
      *@return
      */
	public  static  boolean sendMessageToEndPoint(final String url, final String message) {
		HttpURLConnection connection = null;
		BufferedReader in = null;
		try {
			if (log.isDebugEnabled()) {
				log.debug("Attempting to access " + url);
			}
			final URL logoutUrl = new URL(url);
			final String output = "logoutRequest=" + URLEncoder.encode(message, "UTF-8");
			connection = (HttpURLConnection) logoutUrl.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setReadTimeout(500000);
			connection.setConnectTimeout(500000);
			connection.setRequestProperty("Content-Length", "" + Integer.toString(output.getBytes().length));
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			final DataOutputStream printout = new DataOutputStream(connection.getOutputStream());
			printout.writeBytes(output);
			printout.flush();
			printout.close();

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while (in.readLine() != null) {
			}

			if (log.isDebugEnabled()) {
				log.debug("Finished sending message to" + url);
			}
			return true;
		} catch (final Exception e) {
			log.error(e, e);
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final IOException e) {
					// can't do anything
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String urlOrder="http://order.kiiik.com:8082/order/";
		String urlCart="http://cart.kiiik.com:8081/cart/";
				 //http://localhost:8088/springsecurity3/welcome.html
		urlCart = "http://localhost:8088/springsecurity3/welcome.html";
				   //ST-1-MYzKzjuS0cSrquXiaV1J-cas.kiiiik.com
		           //ST-5-B1U2GV1IuKGa6TLSfOhz-cas.kiiiik.com
		String st = "ST-1-wEuOBduWHL4wYtduBjNb-http://cas.kiiik.com:8088";
		String msg = "<samlp:LogoutRequest  xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" "
				+ " ID=\"LR-2-xLwgvikF1qbbkjHdJKdtvzIZvDWY9zms5cj\" "
				+ " Version=\"2.0\" IssueInstant=\"2018-07-30T13:28:35Z\">"
				+ " <saml:NameID xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\">@NOT_USED@</saml:NameID>"
				+ " <samlp:SessionIndex>"+st+"</samlp:SessionIndex>"
				+ " </samlp:LogoutRequest>";
		//sendMessageToEndPoint(urlOrder,msg);
		sendMessageToEndPoint(urlCart,msg);
		/**
		 * 操作顺序   cart1  springsecurity3同时登录   
		 * 调用cart1的登出，然后将TGT删除，然后根据ST通知子系统登出，然后可以成功！！！！！！
		 */
	}

}
