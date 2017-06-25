package com.app.stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionExUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;

public class StockDbUtils {
	private static Log log = LogFactory.getLog(StockDbUtils.class);
	public static ConnectionExUtils<Stock> dbUtils = null;
	private static boolean createTable = false;
    public StockDbUtils(String dbName){
    	Connection conn = H2DBUtil.getConnection(dbName);
		dbUtils = new ConnectionExUtils<Stock>(conn);
    }
    public static void save(Stock p){
    	try {
			dbUtils.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void initConnection(String dbName) {
		try {
			Connection conn = H2DBUtil.getConnection(dbName);
			dbUtils = new ConnectionExUtils<Stock>(conn);
			log.info(dbUtils.queryForMap("select 1+1 from dual", Map.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exeDLL() {
		if (createTable) {
			String Stock = "create table Stock(id varchar(50),code varchar(8),name varchar(50),exchange varchar(2),classify varchar(2),province varchar(2),industry varchar(2),timeofmarket varchar(20))";
			try {
				dbUtils.update(Stock);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		try {
			H2Manager.start();
			initConnection("app");
			/*exeDLL();*/

			/*log.info(dbUtils.update("delete from Stock"));
			Stock obj = new Stock();
			obj.setIp("127.0.0.2");
			obj.setPort(80);
			 dbUtils.save(obj); 

			log.info("queryForList:"
					+ dbUtils.queryForList("select * from Stock", Stock.class));
			log.info("queryForMap:"
					+ dbUtils.queryForMap("select * from Stock", Map.class));
			log.info("queryForMap:"
					+ dbUtils.update("update Stock set port=8085"));*/
			/*String sql = "select ip,port from Stock";
			List<Stock> users = dbUtils.queryForList(sql, Stock.class);
			for (int i = 0; i < users.size(); i++) {
				Stock p = users.get(i);
				log.info("ip:" + p.getIp() + ",port:" + p.getPort());
			}*/
			/*sql = "select ip,port from Stock where ip='127.0.0.1'";
			Stock user = dbUtils.queryForObject(sql, Stock.class);
			log.info("queryForObject:" + user);*/
			dbUtils.close();
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
