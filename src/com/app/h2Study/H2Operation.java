package com.app.h2Study;

import java.sql.Connection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionExUtils;
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
			TRUNCATECompare(); deleteCompare();
			/*String name = "test1";
			insertDataBatch(name, false);
			System.out.println("1 over!");
			String name2 = "test2";
			insertDataBatch(name2, false);
			System.out.println("2 over!");*/
		} catch (Exception e) {
		}
		//System.exit(1);
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
