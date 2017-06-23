package com.cyb.comconection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.app.proxy.Proxy;
import com.cyb.h2.H2DBUtil;
/**
	ResultSetHandler 接口提供了一个单独的方法：Object handle (java.sql.ResultSet .rs)。
	ResultSetHandler 接口的实现类
	ArrayHandler：把结果集中的第一行数据转成对象数组。
	ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
	BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
	BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
	ColumnListHandler：将结果集中某一列的数据存放到List中。
	KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
	MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
	MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 * @author DHUser
 * @param <T>
 */
public class ConnectionExUtils<T> extends QueryRunner {
	public  Connection conn = null;  
	public QueryRunner query = null;
	public  void close(){
		DbUtils.closeQuietly(conn); 
	}
	public ConnectionExUtils(Connection conn){
		if(this.conn == null){
			this.conn = conn;
		}else{
			new ConnectionExUtils<T>(this.conn);
		}
		if(query==null){
			query = new QueryRunner();
		}
	}
	public static void main(String[] args) throws SQLException {
	     Connection conn = H2DBUtil.getConnection("chenyb");
	     ConnectionExUtils<Proxy> dbUtils = new ConnectionExUtils<Proxy>(conn);
	     System.out.println(dbUtils.queryForMap("select 1+1 from dual", Map.class));
	     System.exit(0);
	}
	
	public  List<T> queryForList(String sql,Class<T> cls) throws SQLException{
		List<T> results = this.query.query(conn, sql, new BeanListHandler<T>(cls));    
		return results;
	}
	public  T queryForObject(String sql,Class<T> cls) throws SQLException{
		T t = this.query.query(conn, sql, new BeanHandler<T>(cls));  
		return t;
	}
	public Map<String,Object> queryForMap(String sql,@SuppressWarnings("rawtypes") Class cls) throws SQLException{
		List<Map<String,Object>> results = (List<Map<String,Object>>) this.query.query(conn, sql, new MapListHandler());         
		return results.get(0);
	}
	//添加save方法
    /**
     * 对传递的bean进行分析
     * 将t对象转成insert into users
     */
    public <T> T save(T t) throws Exception{
        Class<?> cls = t.getClass();
        Table table = cls.getAnnotation(Table.class);
        String tableName = table.name();
        String sql = "insert into "+tableName;
        String cols="(";
        String values="values(";

        //获取所有声明的字段
        Field[] fs = cls.getDeclaredFields();
        //遍历所有字段
        for(Field f:fs){
            if(f.isAnnotationPresent(Column.class)){
                //获取列名
                String colName = f.getName();
                //获取column的对象
                Column col = f.getAnnotation(Column.class);
                if(col.name()!=null && !col.name().trim().equals("")){
                    colName=col.name();
                } 
                //获取列值
                f.setAccessible(true);
                //获取列值
                Object value = f.get(t);
                if(cols.equals("(")){ 
                    cols+=colName;
                    if(value instanceof String){
                        values+="'"+value+"'";
                    }else{
                        values+=value;
                    }

                }else{
                    cols+=","+colName;
                    if(value instanceof String){
                        values+=",'"+value+"'";
                    }else{
                        values+=","+value;
                    }
                }
            }
        }
        cols+=")";
        values+=")";
        sql = sql+cols+" "+values;
        System.err.println(sql);
        try{
        	update(sql);
        }catch(Exception e){}
        return t;
    }
    @Override
    public int[] batch(Connection arg0, String arg1, Object[][] arg2){
        try {
            return super.batch(arg0, arg1, arg2);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
    @Override
    public int[] batch(String sql, Object[][] params){
        try {
            return super.batch(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    protected void close(Connection conn) {
        try {
            super.close(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override  
    protected void close(ResultSet rs){
        try {
            super.close(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    protected void close(Statement stmt){
        try {
            super.close(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    public void fillStatement(PreparedStatement arg0, Object... arg1)
            {
        try {
            super.fillStatement(arg0, arg1);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public void fillStatementWithBean(PreparedStatement arg0, Object arg1,
            PropertyDescriptor[] arg2){
        try {
            super.fillStatementWithBean(arg0, arg1, arg2);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public void fillStatementWithBean(PreparedStatement arg0, Object arg1,
            String... arg2) {
        try {
            super.fillStatementWithBean(arg0, arg1, arg2);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public DataSource getDataSource() {
        return super.getDataSource();
    }

    @Override
    protected Connection prepareConnection() {
        try {
            return super.prepareConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    protected PreparedStatement prepareStatement(Connection conn, String sql)
            {
        try {
            return super.prepareStatement(conn, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public <T> T query(Connection conn, String sql, Object param,
            ResultSetHandler<T> rsh) {
        try {
            return super.query(conn, sql, param, rsh);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public <T> T query(Connection conn, String sql, Object[] params,
            ResultSetHandler<T> rsh) {
        try {
            return super.query(conn, sql, params, rsh);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    public <T> T query(Connection arg0, String arg1, ResultSetHandler<T> arg2,
            Object... arg3){
        try {
            return super.query(arg0, arg1, arg2, arg3);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    } 

    @Override
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh)
             {
        try {
            return super.query(conn, sql, rsh);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
         {
        try {
            return super.query(sql, param, rsh);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
             {
        try {
            return super.query(sql, params, rsh);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
             {
        try {
            return super.query(sql, rsh, params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh){
        try {
            return super.query(sql, rsh);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    protected void rethrow(SQLException cause, String sql, Object... params)
             {
        try {
            super.rethrow(cause, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public int update(Connection arg0, String arg1, Object... arg2)
             {
        try {
            return super.update(arg0, arg1, arg2);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public int update(Connection conn, String sql, Object param)
             {
        try {
            return super.update(conn, sql, param);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public int update(Connection conn, String sql) {
        try {
            return super.update(conn, sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public int update(String sql, Object... params){
        try {
            return super.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    @Override
    public int update(String sql, Object param){
        try {
            return super.update(conn,sql, param);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }
    public int update1(String sql){
		try {
            //事务开始
           return  this.query.update(conn,sql);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
	}
    public int update(String sql) {
        try {
            return super.update(conn,sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    protected ResultSet wrap(ResultSet rs) {
        return super.wrap(rs);
    }
}  