package com.app.h2Study;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionDataSourceUtils;
import com.cyb.comconection.ConnectionExUtils;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.h2.H2DBUtil;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月8日
 */
public class H2Operation {
	static ConnectionExUtils<Object> dbUtils = null;
	static String url = "jdbc:h2:tcp://localhost/d:/data/db/";
	Log log = LogFactory.getLog(H2Operation.class);

	public static void main(String[] args) {
		try {
			/*new Thread(new Runnable() {
				public void run() {
					String name = "test1";
					insertDataBatch(name, false);
					System.out.println("1 over!");
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					String name = "test2";
					insertDataBatch(name, false);
					System.out.println("2 over!");
				}
			}).start();*/
			/*TRUNCATECompare();
			deleteCompare();*/
			/*String name = "test1";
			insertDataBatch(name, false);
			System.out.println("1 over!");
			String name2 = "test2";
			insertDataBatch(name2, false);
			System.out.println("2 over!");*/
			showRecord();
			//deleteRecord();
		} catch (Exception e) {
		}
		//System.exit(1);
	}
	@SuppressWarnings("rawtypes")
	static
	ConnectionDataSourceUtils<List<Map>> dbUtils1 = new ConnectionDataSourceUtils<List<Map>>("h2-1");
	@SuppressWarnings("rawtypes")
	static
	ConnectionDataSourceUtils<Map> dbUtils2 = new ConnectionDataSourceUtils<Map>("h2-2");
	
	private static void showRecord() throws SQLException {
		String sql = "select count(1) from test";
		@SuppressWarnings("rawtypes")
		List<List<Map>> rs = dbUtils1.queryForMap(sql, List.class);
		System.out.println(rs);
		
		@SuppressWarnings("rawtypes")
		List<Map> rs1 = dbUtils2.queryForMap(sql, Map.class);
		System.out.println(rs1);
		
	}
	@SuppressWarnings("unused")
	private static void deleteRecord() {
		String sql1 = "TRUNCATE TABLE TEST ";
		String sql2 = "delete from TEST ";
		dbUtils1.update(sql1);
		dbUtils1.update(sql2);
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
				System.out.println(name + " index is " + i);
			}
		}
		System.out.println("save over！");
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
				System.out.println(name + " index is " + i);
			}
		}
		System.out.println("save over！");
		utils.close();
	}
	static volatile long seq = 0;
	public static void insertDataBatch(String name, boolean create,ConnectionUtils<Object> utils,int wan) throws SQLException {
		for (int i = 0; i <= wan * 10000; i++) {
			utils.update("INSERT INTO " + name + " VALUES(" + i + ", 'Hello" + i + "')");
			if (i % 10000 == 0&&i>0) {
				H2Operation.showRecord();
			}
		}
		System.out.println("save over！");
		utils.close();
	}
	
	public static void insertDataBatch2(String name, boolean create,ConnectionUtils<Object> utils,int wan) {
		if (create) {
			utils.update("DROP TABLE IF EXISTS " + name);
			utils.update("CREATE TABLE " + name + "(ID INT PRIMARY KEY,NAME VARCHAR(255))");
		}
		String sql = "INSERT INTO " + name + " VALUES( ? , ?)";
		Object[][] param = new Object[10000][2];
		for(int j=0;j<=wan;j++){
			for (int i = 0; i < 10000; i++) {
				param[i][0]="chenyuanbaoiechenybzzuchenyb"+i;
				param[i][1]="hahahhahahahahahahahhaahhhah"+i;
			}
			utils.batch(sql, param);//同一个sql，多个参数
			System.out.println("向表"+name + "中插入" + (j+1)+"万条记录！");
			param = new Object[10000][2];
		}
		System.out.println("save over！");
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
		System.out.println("TRUNCATE over！"+(e-s)+" ms");
	}

	public static void deleteCompare() {
		long s = System.currentTimeMillis();
		String sql = "delete from TEST2 ";
		Connection conn = H2DBUtil.getConnectionByPath(url + "test2");
		dbUtils = new ConnectionExUtils<Object>(conn);
		dbUtils.update(sql);
		dbUtils.close();
		long e = System.currentTimeMillis();
		System.out.println("delete over！"+(e-s)+" ms");
	}
}
