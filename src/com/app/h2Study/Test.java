package com.app.h2Study;
import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.comconection.DBPoolConnection;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月20日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);
	static ConnectionUtils<Object> dbUtilsWithLog = null;
	static ConnectionUtils<Object> dbUtilsNoLog = null;
	public static void main(String[] args) throws Exception {
		DBPoolConnection dbp =new DBPoolConnection("h2-1"); 
		Connection conn = dbp.getConnection();
		System.out.println(conn);
		/*ConnectionUtils<Object> dbUtilsWithLog = new ConnectionUtils<Object>(conn);//无日志数据库文件
		H2Operation.insertDataBatch2("test", false, dbUtilsWithLog, 1);*/
		//H2Operation.deleteRecord();
	}
}
