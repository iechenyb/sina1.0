package com.app.proxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.csdn.ParamsUtil;
import com.cyb.comconection.ConnectionExUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;

public class ProxyDbUtils {
	private static Log log = LogFactory.getLog(ProxyDbUtils.class);
	public static ConnectionExUtils<Proxy> dbUtils = null;
	private static String dbPath ;
	static{
		dbPath = ParamsUtil.get("tcpPrix")+ParamsUtil.get("dbPath"+ParamsUtil.get("etc"));
    }
    public ProxyDbUtils(String dbName){
    	Connection conn = H2DBUtil.getConnectionByPath(dbPath+dbName);
    	System.out.println("数据库路径："+dbPath+dbName);
		dbUtils = new ConnectionExUtils<Proxy>(conn);
		exeDLL(conn);
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
			Connection conn = H2DBUtil.getConnectionByPath(dbPath+dbName);
			dbUtils = new ConnectionExUtils<Proxy>(conn);
			log.info(dbUtils.queryForMap("select 1+1 from dual", Map.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void exeDLL(Connection conn) {
		if (!H2DBUtil.isExist(conn, "proxy")) {
			String proxy = " create table proxy(ip varchar(16),port int,useable int)";
			try {
				dbUtils.update(proxy);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void updateUseable() throws SQLException{
		List<Proxy> proxys = dbUtils.queryForList("select * from proxy", Proxy.class);
		for (int i = 0; i < proxys.size(); i++) {
			Proxy proxy = proxys.get(i);
			if (com.app.proxy.ProxyUtils.isUseable(ProxyEvil.url, proxy.getIp(),
			proxy.getPort())) {
				dbUtils.update("update proxy set useable=1 where ip='"+proxy.getIp()+"' and port='"+proxy.getPort()+"'");
			}
		}
	}
	public static void main(String[] args) throws Exception {
		try {
			H2Manager.start();
			new ProxyDbUtils("app");
			dbUtils.close();
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
}
