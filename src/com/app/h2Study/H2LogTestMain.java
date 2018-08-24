package com.app.h2Study;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.task.SuperTaskBuilder;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.comconection.DBPoolConnection;
/*import static 包名.类名.静态成员变量;
import static 包名.类名.静态成员函数;*/
/**
 *作者 : iechenyb<br>
 *类描述: 测试目的：
 * 解决问题：原有的行情db每日的数据虽然定时全部清理，但是日志文件却越来越大。
 * 解决方法：考虑不用存储系统的存储日志和会话日志，是否可以将数据库文件大小控制最小。
 * 测试内容：测试不记录日志和记录日志的情况下：
 * 1 删除数据库数据文件的大小变化
 * 2 不断的增加数据看文件大小的变化。 <br>
 *创建时间: 2018年8月20日
 */
public class H2LogTestMain {
	
	Log log = LogFactory.getLog(H2LogTestMain.class);
	static ConnectionUtils<Object> dbUtilsWithLog = null;
	static ConnectionUtils<Object> dbUtilsNoLog = null;
	static boolean create = true;
	public static String name ="test";
	
	public static void main(String[] args) throws Exception {
		enter();
		new SuperTaskBuilder
		.Builder()//静态类
        .target(H2LogTask.class)
        .corn("0 */7 * * * ?")
        .builder();
	}
	static int total = 50;
	public static void enter() throws Exception {
		//H2Manager.start();//需要启动h2服务
		//显示文件大小
		H2Operation.showFileSize();
		H2Operation.showRecord();
		final DBPoolConnection dbp1 =new DBPoolConnection("h2-1"); //无日志数据库文件
		final DBPoolConnection dbp2 =new DBPoolConnection("h2-2"); 
		//清除数据
		DBPoolConnection.delete("delete from "+H2LogTestMain.name, dbp1.getConnection());
		DBPoolConnection.delete("delete from "+H2LogTestMain.name, dbp2.getConnection());
		//显示文件大小
		H2Operation.showFileSize();
		H2Operation.showRecord();
		if (!create) {
			dbUtilsWithLog.update("DROP TABLE IF EXISTS " + name);
			dbUtilsWithLog.update("CREATE TABLE " + name + "(ID INT,NAME VARCHAR(255))");
			dbUtilsNoLog.update("DROP TABLE IF EXISTS " + name);
			dbUtilsNoLog.update("CREATE TABLE " + name + "(ID INT,NAME VARCHAR(255))");
			System.out.println("数据清除完成！");
		}
		List<FutureTask<Object>> tasks = new ArrayList<>();
		for(int i=0;i<1;i++){
			Connection con1 = dbp1.getConnection();
			Connection con2 = dbp2.getConnection();
			dbUtilsNoLog = new ConnectionUtils<Object>(con1);//无日志数据库文件
			dbUtilsWithLog = new ConnectionUtils<Object>(con2);//有日志数据库文件
			System.out.println(con1);
			System.out.println(con2);
			if(con1==null||con2==null)
				throw new Exception("数据源为空！");
			Callable<Object> t1 = new  Callable<Object>() {
				public Object call() {
					long s = System.currentTimeMillis();
					try {
						System.out.println("插入无日志记录......");
						//H2Operation.insertDataBatch(name, true, dbUtilsNoLog,total);
						H2Operation.insertDataBatch2("test",dbp1.getConnection(),total);
						long e = System.currentTimeMillis();
						System.out.println("nolog 插入数据结束！执行毫秒"+(e-s));
						return null;
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}//批量插入数据 各存储一千万
				
				}
			};
			tasks.add(new FutureTask<Object>(t1));
			Callable<Object> t2 = new  Callable<Object>() {
				public Object call() {
					try {
						long s = System.currentTimeMillis();
						System.out.println("插入带日志记录......");
						//H2Operation.insertDataBatch(name, true,dbUtilsWithLog,total);
						H2Operation.insertDataBatch2("test",dbp2.getConnection(),total);
						long e = System.currentTimeMillis();
						System.out.println("withlog 插入数据结束！执行毫秒"+(e-s));
						return null;
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}//批量插入数据 各存储一千万
				}
			};
			tasks.add(new FutureTask<Object>(t2));
		}
		for(int i=0;i<tasks.size();i++){
			new Thread(tasks.get(i)).start();
		}
		for(int i=0;i<tasks.size();i++){
			tasks.get(i).get();
		}
		//显示记录数
		H2Operation.showRecord();
		//显示文件大小
		H2Operation.showFileSize();
		//清除数据
		DBPoolConnection.delete("delete from "+H2LogTestMain.name, dbp1.getConnection());
		DBPoolConnection.delete("delete from "+H2LogTestMain.name, dbp2.getConnection());
		//显示文件大小
		H2Operation.showRecord();
		H2Operation.showFileSize();
		System.out.println("====================================");
	}
	
}
