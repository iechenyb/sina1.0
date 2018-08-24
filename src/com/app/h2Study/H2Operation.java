package com.app.h2Study;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionDataSourcePoolUtils;
import com.cyb.comconection.ConnectionExUtils;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.comconection.DBPoolConnection;
import com.cyb.h2.H2DBUtil;
import com.cyb.property.PropertyUtil;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月8日
 */
public class H2Operation {
	static ConnectionExUtils<Object> dbUtils = null;
	static String url = "jdbc:h2:tcp://localhost/d:/data/db/";
	static Log log = LogFactory.getLog(H2Operation.class);

	public static void main(String[] args) {
		try {
			/*new Thread(new Runnable() {
				public void run() {
					String name = "test1";
					insertDataBatch(name, false);
					log.info("1 over!");
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					String name = "test2";
					insertDataBatch(name, false);
					log.info("2 over!");
				}
			}).start();*/
			/*TRUNCATECompare();
			deleteCompare();*/
			/*String name = "test1";
			insertDataBatch(name, false);
			log.info("1 over!");
			String name2 = "test2";
			insertDataBatch(name2, false);
			log.info("2 over!");*/
			//showRecord();
			//deleteRecord();
			DBPoolConnection dbp1 =new DBPoolConnection("h2-1"); //无日志数据库文件
			DBPoolConnection dbp2 =new DBPoolConnection("h2-2"); 
			insertDataBatch2("test",dbp1.getConnection(),10);
			System.out.println("over!");
		} catch (Exception e) {
		}
		//System.exit(1);
	}
	@SuppressWarnings("rawtypes")
	static //nolog
	ConnectionDataSourcePoolUtils<List<Map>> dbUtils1 = new ConnectionDataSourcePoolUtils<List<Map>>("h2-1");
	@SuppressWarnings("rawtypes")
	static //withlog
	ConnectionDataSourcePoolUtils<Map> dbUtils2 = new ConnectionDataSourcePoolUtils<Map>("h2-2");
	static void showFileSize(){
		String[] dbName=new String[]{"h2-1","h2-2"};
		for(int i=0;i<dbName.length;i++){
			Properties p = PropertyUtil.load(dbName[i]);
			String url = p.get("url").toString();
			String dbPath = url.substring(url.indexOf("d:/"), url.indexOf(";"));
			File file = new File(dbPath+".h2.db");
			log.info(file.getName()+"\t"+file.length()/1024+"k");
		}
	}
	static void showRecord() throws SQLException {
		String sql = "select count(1) from test";
		@SuppressWarnings("rawtypes")
		List<List<Map>> rs = dbUtils1.queryForMap(sql, List.class);
		log.info(rs);
		
		@SuppressWarnings("rawtypes")
		List<Map> rs1 = dbUtils2.queryForMap(sql, Map.class);
		log.info(rs1);
		
	}
	static void deleteRecord() {
		//String sql1 = "TRUNCATE TABLE TEST ";
		String sql2 = "delete from  "+H2LogTestMain.name;
		dbUtils1.update(sql2);//nolog
		dbUtils2.update(sql2);//withlog
	}
	public static void insertDataBatch(String name, boolean create) {
		Connection conn = H2DBUtil.getConnectionByPath(url + name);
		dbUtils = new ConnectionExUtils<Object>(conn);
		if (create) {
			dbUtils.update("DROP TABLE IF EXISTS " + name);
			dbUtils.update("CREATE TABLE " + name + "(ID INT PRIMARY KEY,NAME VARCHAR(255))");
		}
		for (int i = 0; i <= 100 * 10000; i++) {
			dbUtils.update("INSERT INTO " + name + " VALUES(" + i + ", 'Hello" + i + "')");
			if (i % 10000 == 0) {
				log.info(name + " index is " + i);
			}
		}
		log.info("save over！");
		dbUtils.close();
	}
	
	public static void insertDataBatch(String name, boolean create,ConnectionUtils<Object> utils) {
		if (create) {
			utils.update("DROP TABLE IF EXISTS " + name);
			utils.update("CREATE TABLE " + name + "(ID INT PRIMARY KEY,NAME VARCHAR(255))");
		}
		for (int i = 0; i <= 100 * 10000; i++) {
			utils.update("INSERT INTO " + name + " VALUES(" + i + ", 'Hello" + i + "')");
			if (i % 10000 == 0) {
				log.info(name + " index is " + i);
			}
		}
		log.info("save over！");
		utils.close();
	}
	static volatile long seq = 0;
	public static void insertDataBatch(String name, boolean create,ConnectionUtils<Object> utils,int wan) throws SQLException {
		for (int i = 0; i <= wan * 10000; i++) {
			utils.update("INSERT INTO " + name + " VALUES(" + i + ", 'Hello" + i + "')");
			if (i % 10000 == 0&&i>0) {
				//H2Operation.showRecord();
			}
		}
		log.info("save over！");
		utils.close();
	}
	
	public static void insertDataBatch2(String name,Connection con,int wan) throws SQLException {
		ConnectionUtils<Object> utils = new ConnectionUtils<>();
		String sql = "INSERT INTO " + name + " VALUES( ? , ?)";
		Object[][] param = new Object[10000][2];
		for(int j=0;j<=wan;j++){
			for (int i = 0; i < 10000; i++) {
				param[i][0]=i;
				param[i][1]="hahahhahahahahahahahhaahhhah"+i;
			}
			utils.batch(con,sql, param);//同一个sql，多个参数
			param = new Object[10000][2];
		}
		log.info("save over！");
		utils.close();
	}
	public static void TRUNCATECompare() {
		long s = System.currentTimeMillis();
		String sql = "TRUNCATE TABLE TEST1 ";
		Connection conn = H2DBUtil.getConnectionByPath(url + "test1");
		dbUtils = new ConnectionExUtils<Object>(conn);
		dbUtils.update(sql);
		dbUtils.close();
		long e = System.currentTimeMillis();
		log.info("TRUNCATE over！"+(e-s)+" ms");
	}

	public static void deleteCompare() {
		long s = System.currentTimeMillis();
		String sql = "delete from TEST2 ";
		Connection conn = H2DBUtil.getConnectionByPath(url + "test2");
		dbUtils = new ConnectionExUtils<Object>(conn);
		dbUtils.update(sql);
		dbUtils.close();
		long e = System.currentTimeMillis();
		log.info("delete over！"+(e-s)+" ms");
	}
}
