package com.app.iqiyi;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.csdn.ParamsUtil;
import com.cyb.comconection.ConnectionExUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;

public class VedioDbUtils {
	private static Log log = LogFactory.getLog(VedioDbUtils.class);
	public static ConnectionExUtils<Vedio> dbUtils = null;
	private static String dbPath ;
	static{
	   dbPath = ParamsUtil.get("tcpPrix")+ParamsUtil.get("dbPath"+ParamsUtil.get("etc"));
	}
    public VedioDbUtils(String dbName){
    	Connection conn = H2DBUtil.getConnectionByPath(dbPath+dbName);
    	log.info("数据库路径："+dbPath+dbName);
		dbUtils = new ConnectionExUtils<Vedio>(conn);
		exeDLL(conn);
    }
    public static void save(Vedio p){
    	try {
			dbUtils.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void initConnection(String dbName) {
		try {
			Connection conn = H2DBUtil.getConnectionByPath(dbName);
			dbUtils = new ConnectionExUtils<Vedio>(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static List<Vedio> getVedio(String type) throws SQLException{
    	return dbUtils.queryForList("select * from vedio where type='"+type+"'", Vedio.class);
    }
	public static void exeDLL(Connection conn) {
		if (!H2DBUtil.isExist(conn, "VEDIO")) {
			String Vedio = " create table Vedio(name varchar(50),url varchar(100),type varchar(30))";
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
			new VedioDbUtils("vedio");
			dbUtils.close();
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
