package com.app.webeye;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.utils.ParamsUtilLocal;
import com.cyb.comconection.ConnectionExUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;
import com.cyb.reflect.DDLUtils;

public class NetDbUtils {
	private static Log log = LogFactory.getLog(NetDbUtils.class);
	public static ConnectionExUtils<NetResult> dbUtils = null;
	private static String dbPath ;
	static{
	   
	}
    public NetDbUtils(String dbName){
    	dbPath = ParamsUtilLocal.get("tcpPrix")+ParamsUtilLocal.get("dbPath"+ParamsUtilLocal.get("etc"));
    	Connection conn = H2DBUtil.getConnectionByPath(dbPath+dbName);
    	System.out.println("数据库路径："+dbPath+dbName);
		dbUtils = new ConnectionExUtils<NetResult>(conn);
		try {
			exeDLL(conn);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    public static void save(NetResult p){
    	try {
			dbUtils.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void initConnection(String dbName) {
		try {
			Connection conn = H2DBUtil.getConnectionByPath(dbName);
			dbUtils = new ConnectionExUtils<NetResult>(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static List<NetResult> getVedio(String type) throws SQLException{
    	return dbUtils.queryForList("select * from vedio where type='"+type+"'", NetResult.class);
    }
	public static void exeDLL(Connection conn) throws IllegalArgumentException, IllegalAccessException {
		NetResult nr = new NetResult();
		if (!H2DBUtil.isExist(conn, DDLUtils.tableName(nr))) {
			String Vedio = DDLUtils.createSql(nr);
			try {
				dbUtils.update(Vedio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		try {
			H2Manager.start();
			new NetDbUtils("NetDB");
			dbUtils.close();
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
