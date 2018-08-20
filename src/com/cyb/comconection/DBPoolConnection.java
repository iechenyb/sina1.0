package com.cyb.comconection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
 
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月20日
 */
public class DBPoolConnection {
	Log log = LogFactory.getLog(DBPoolConnection.class);
	private static DBPoolConnection dbPoolConnection = null;
    private static DruidDataSource druidDataSource = null;
    
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
    	Properties properties = null;
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
    public static synchronized DBPoolConnection getInstance(){
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
    private static Properties loadPropertiesFile(String fullFile) {
        String webRootPath = System.getProperty("user.dir");//工程名
        System.out.println("工程路径："+webRootPath);
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
    
    
    public static void main(String[] args) throws Exception {
    	//执行一条sql语句测试
    	DBPoolConnection dbp =new DBPoolConnection("db_server"); 
    			//DBPoolConnection.getInstance();    //获取数据连接池单例
        DruidPooledConnection conn = null;
        PreparedStatement ps = null;
        try {
        	String sql = "update sys_user set name='tbuser'  where id=2;";
            conn = dbp.getConnection();    //从数据库连接池中获取数据库连接
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps){
                    ps.close();
                }
                if (null != conn){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
