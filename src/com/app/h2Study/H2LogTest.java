package com.app.h2Study;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionDataSourceUtils;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;
/**
 *作者 : iechenyb<br>
 *类描述: 测试目的：
 * 解决问题：原有的行情db每日的数据虽然定时全部清理，但是日志文件却越来越大。
 * 解决方法：考虑不用存储系统的存储日志和会话日志，是否可以将数据库文件大小控制最小。
 * 测试内容：测试不记录日志和记录日志的情况下：
 * 1 删除数据库数据文件的大小变化
 * 2 不断的增加数据看文件大小的变化。 <br>
 *创建时间: 2018年8月20日
 */
public class H2LogTest {
	
	Log log = LogFactory.getLog(H2LogTest.class);
	static String params=";LOG=0;CACHE_SIZE=65536;LOCK_MODE=0;UNDO_LOG=0";
	static String urlNoLog = "jdbc:h2:tcp://localhost/d:/data/db/";
	static String urlWithLog = "jdbc:h2:tcp://localhost/d:/data/db/";
	static ConnectionUtils<Object> dbUtilsWithLog = null;
	static ConnectionUtils<Object> dbUtilsNoLog = null;
	static boolean create = true;
	static String name ="test";
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		H2Manager.start();//需要启动h2服务
		Connection con1 = H2DBUtil.getConnectionByPath(urlNoLog+"dbnolog", params);
		Connection con2 = H2DBUtil.getConnectionByPath(urlWithLog+"dbwithlog");
		if(con1==null||con2==null)
			throw new Exception("数据源为空！");
		
		dbUtilsWithLog = new ConnectionUtils<Object>(con1);//无日志数据库文件
		dbUtilsNoLog = new ConnectionUtils<Object>(con2);//有日志数据库文件
		
		ConnectionDataSourceUtils<Object> dbUtils1 = new ConnectionDataSourceUtils<Object>("h2-1");
		ConnectionDataSourceUtils<Object> dbUtils2 = new ConnectionDataSourceUtils<Object>("h2-2");
		if (create) {
			dbUtilsWithLog.update("DROP TABLE IF EXISTS " + name);
			dbUtilsWithLog.update("CREATE TABLE " + name + "(ID INT,NAME VARCHAR(255))");
			dbUtilsNoLog.update("DROP TABLE IF EXISTS " + name);
			dbUtilsNoLog.update("CREATE TABLE " + name + "(ID INT,NAME VARCHAR(255))");
		}
		System.out.println("数据清除完成！");
		for(int i=0;i<10;i++){
		    new Thread(new  Runnable() {
				public void run() {
					try {
						H2Operation.insertDataBatch(name, true, dbUtilsWithLog,1000);
					} catch (SQLException e) {
						e.printStackTrace();
						
					}//批量插入数据 各存储一千万
				
				}
			}).start();
			new Thread(new  Runnable() {
				public void run() {
					try {
						H2Operation.insertDataBatch(name, true,dbUtilsNoLog,1000);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}//批量插入数据 各存储一千万
				}
			}).start();
		}
		//showRecord();
	}
	
}
