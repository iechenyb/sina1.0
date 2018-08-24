package com.cyb.comconection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
 
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.app.h2Study.H2LogTestMain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月20日
 */
public class DBPoolConnection {
	Log log = LogFactory.getLog(DBPoolConnection.class);
	private  DBPoolConnection dbPoolConnection = null;
    private  DruidDataSource druidDataSource = null;
    private  Properties properties = null;
    
   /* static {
    	Properties properties = loadPropertiesFile("config\\db_server.properties");
        try {
            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);    //DruidDataSrouce工厂模式
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
   public  DBPoolConnection(){
    	
    }
    
    public DBPoolConnection(String dbFileName) throws Exception{
    	
    	if(!dbFileName.endsWith("properties")){
    		properties = loadPropertiesFile("config\\"+dbFileName+".properties");
    	}else{
    		properties =loadPropertiesFile("config\\"+dbFileName);
    	}
    	druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);    //DruidDataSrouce工厂模式
    }
    /**
     * 数据库连接池单例
     * @return
     */
    public  synchronized DBPoolConnection getInstance(){
        if (null == dbPoolConnection){
            dbPoolConnection = new DBPoolConnection();
        }
        return dbPoolConnection;
    }
    
    /**
     * 返回druid数据库连接
     * @return
     * @throws SQLException
     */
    public DruidPooledConnection getConnection() throws SQLException{
        return druidDataSource.getConnection();
    }
    /**
     * @param string 配置文件名
     * @return Properties对象
     */
    private  Properties loadPropertiesFile(String fullFile) {
        String webRootPath = System.getProperty("user.dir");//工程名
        if (null == fullFile || fullFile.equals("")){
            throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
        }
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(webRootPath + "\\" + fullFile));
            p = new Properties();
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }
    public static void query(String sql,Connection con,String[] colums) throws SQLException{
    	PreparedStatement ps = con.prepareStatement(sql);
    	ResultSet rs = ps.executeQuery();
        while (rs.next()){
        	for(int i=0;i<colums.length;i++){
        		System.out.print(rs.getString(colums[i])+"\t");
        	}
        	System.out.println();
        }
    }
    public static void delete(String sql,Connection con) throws SQLException{
    	long s = System.currentTimeMillis();
    	PreparedStatement ps = con.prepareStatement(sql);
    	int count = ps.executeUpdate();
    	System.out.println(con+",删除记录总数："+count+",执行耗时："+(System.currentTimeMillis()-s));
    }
    public static void main(String[] args) throws Exception {
    	//执行一条sql语句测试
    	DBPoolConnection dbp =new DBPoolConnection("h2-1"); 
    	DBPoolConnection dbp2 =new DBPoolConnection("h2-2"); 
        try {
        	String query = "select * from "+H2LogTestMain.name;
        	//query(query,dbp.getConnection(),new String[]{"id","name"});
        	String delete = "delete from "+H2LogTestMain.name;
        	delete(delete,dbp.getConnection());
        	dbp2.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
