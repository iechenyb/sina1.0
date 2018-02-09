package com.app.webeye;

import java.net.HttpURLConnection;
import java.net.URL;

import com.cyb.UUIDUtils;
import com.cyb.date.DateUtil;
import com.cyb.reflect.sql.DDLUtils;

public class UrlTask{
    public static void execute(){
    	NetResult nr;
		String url="http://www.baidu.com";
		for(int i=0;i<1;i++){
			int status = testConnection(url);
			nr = new NetResult();
			nr.setId(UUIDUtils.getUUID());
			nr.setType("url");
			nr.setIp(url);
			nr.setDomain(url);
			nr.setName("百度首页");
			nr.setTime(DateUtil.timeToSec());
			if(status!=200){
				nr.setZt("0");
				nr.setDesc("请求异常!返回状态码"+status);
			}else{
				nr.setZt("1");
				nr.setDesc("请求正常");
			}
			String sql = null;
			try {
				sql = DDLUtils.saveSql(nr);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			try {
				NetDbUtils.dbUtils.save(nr);
				System.out.println(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    }
    public static int testConnection(String address) {
        int status = 404;
        try {
            URL urlObj = new URL(address);
            HttpURLConnection oc = (HttpURLConnection) urlObj.openConnection();
            oc.setUseCaches(false);
            oc.setConnectTimeout(3000); // 设置超时时间
            status = oc.getResponseCode();// 请求状态
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return status;
        }
    }
}