package com.app.proxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionExUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;

public class ProxyDbUtils {
	private static Log log = LogFactory.getLog(ProxyDbUtils.class);
	public static ConnectionExUtils<Proxy> dbUtils = null;
	private static boolean createTable = false;
    public ProxyDbUtils(String dbName){
    	Connection conn = H2DBUtil.getConnection(dbName);
		dbUtils = new ConnectionExUtils<Proxy>(conn);
    }
    public static void save(Proxy p){
    	try {
			dbUtils.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void initConnection(String dbName) {
		try {
			Connection conn = H2DBUtil.getConnection(dbName);
			dbUtils = new ConnectionExUtils<Proxy>(conn);
			log.info(dbUtils.queryForMap("select 1+1 from dual", Map.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exeDLL() {
		if (createTable) {
			String proxy = " create table proxy(ip varchar(16),port int)";
			try {
				dbUtils.update(proxy);
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

			/*log.info(dbUtils.update("delete from proxy"));
			Proxy obj = new Proxy();
			obj.setIp("127.0.0.2");
			obj.setPort(80);
			 dbUtils.save(obj); 

			log.info("queryForList:"
					+ dbUtils.queryForList("select * from proxy", Proxy.class));
			log.info("queryForMap:"
					+ dbUtils.queryForMap("select * from proxy", Map.class));
			log.info("queryForMap:"
					+ dbUtils.update("update proxy set port=8085"));*/
			String sql = "select ip,port from proxy";
			List<Proxy> users = dbUtils.queryForList(sql, Proxy.class);
			for (int i = 0; i < users.size(); i++) {
				Proxy p = users.get(i);
				log.info("ip:" + p.getIp() + ",port:" + p.getPort());
			}
			/*sql = "select ip,port from proxy where ip='127.0.0.1'";
			Proxy user = dbUtils.queryForObject(sql, Proxy.class);
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
