package com.cyb.comconection.pool;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月8日
 */
public class DBTest {
	Log log = LogFactory.getLog(DBTest.class);
	 public static void main(String[] args) {
	        System.out.println("数据库的原数据");
	        testQuery3();
	        testInsert();
	        System.out.println("执行插入后的数据");
	        testQuery3();
	        testUpdate();
	        System.out.println("执行修改后的数据");
	        testQuery3();
	        testDelete();
	        System.out.println("执行删除后的数据");
	        testQuery3();
	        System.out.println("带条件的查询1");
	        testQuery2();
	        System.out.println("带条件的查询2");
	        testQuery1();
	    }



	    /**
	     * 测试插入
	     */
	    private static void testInsert() {
	        Map<String, Object> map = new HashMap<>();
	        map.put("emp_id", 1013);
	        map.put("name", "JDBCUtil测试");
	        map.put("job", "developer");
	        map.put("salary", 10000);
	        map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
	        try {
	            int count = DBUtil.insert("emp_test", map);
	                  } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



	    /**
	     * 测试更新
	     */
	    private static void testUpdate() {
	        Map<String, Object> map = new HashMap<>();
	        map.put("name", "测试更新");

	        Map<String, Object> whereMap = new HashMap<>();
	        whereMap.put("emp_id", "1013");
	        try {
	            int count = DBUtil.update("emp_test", map, whereMap);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }



	    /**
	     * 测试删除
	     */
	    private static void testDelete() {
	        Map<String, Object> whereMap = new HashMap<>();
	        whereMap.put("emp_id", 1013);
	        whereMap.put("job", "developer");
	        try {
	            int count = DBUtil.delete("emp_test", whereMap);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * 查询方式一
	     */
	    public static void testQuery1() {
	        Map<String,Object> whereMap=new HashMap<>();
	        whereMap.put("salary","10000");
	        try {
	            DBUtil.query("emp_test",whereMap);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	     * 查询方式二
	     */
	    public static void testQuery2() {
	        String where = "job = ?  AND salary = ? ";
	        String[] whereArgs = new String[]{"clerk", "3000"};

	        try {
	            List<Map<String, Object>> list = DBUtil.query("emp_test", where, whereArgs);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 查询方式三
	     */
	    public static void testQuery3() {
	        try {
	            List<Map<String, Object>> list = DBUtil.query("emp_test", false, null, null, null, null, null, null, null);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
