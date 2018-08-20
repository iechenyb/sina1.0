package com.cyb.comconection;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
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
import org.apache.commons.lang.StringUtils;

import com.cyb.mybatis.User;

public class ConnectionUtils<T> extends QueryRunner {
	public  Connection conn = null;  
	public QueryRunner query = null;
	public ConnectionUtils(){
	    //String url = "jdbc:h2:tcp://localhost/~/te;AUTO_SERVER=true";  
		String url = "jdbc:h2:tcp://localhost/~/te;AUTO_SERVER=true";  
	    String jdbcDriver = "org.h2.Driver";  
	    String user = "sa";  
	    String password = "111111";  
	    DbUtils.loadDriver(jdbcDriver); 
	    query = new QueryRunner();
	    try {  
	        conn = DriverManager.getConnection(url, user, password);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        //DbUtils.closeQuietly(conn);  
	    }  
	}
	public ConnectionUtils(String path){
		if(StringUtils.isEmpty(path)){ path = "~";}
		String url = "jdbc:h2:tcp://localhost/"+path+""; //;AUTO_SERVER=true 
		System.out.println(url);
	    String jdbcDriver = "org.h2.Driver";  
	    String user = "sa";  
	    String password = "111111";  
	    DbUtils.loadDriver(jdbcDriver); 
	    query = new QueryRunner();
	    try {  
	        conn = DriverManager.getConnection(url, user, password);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        //DbUtils.closeQuietly(conn);  
	    }  
	}
	public  void close(){
		DbUtils.closeQuietly(conn); 
	}
	public ConnectionUtils(Connection conn){
		if(this.conn == null){
			this.conn = conn;
		}else{
			new ConnectionUtils<T>();
		}
		if(query==null){
			query = new QueryRunner();
		}
	}
	public static void main(String[] args) throws SQLException {
	     String sql = "select username,password from usr";
	     ConnectionUtils<User> dbUtils = new ConnectionUtils<User>();
	     List<User> users = dbUtils.queryForList(sql,User.class);
	     for (int i = 0; i < users.size(); i++) {  
	        	User p = users.get(i);  
	            System.out.println("name:" + p.getUsername() + ",pwd:" + p.getPassword());  
	      }  
	     ConnectionUtils<Map<String,Object>> dbUtils1 = new ConnectionUtils<Map<String,Object>>();
	     List<Map<String,Object>> maps = dbUtils1.queryForMap(sql,Map.class);
	     System.out.println(maps);
	     
	     User user = dbUtils.queryForObject(sql,User.class);
	     System.out.println(user.getUsername()+","+user.getPassword());
	     //如何返回一个聚合值 比如记录总数
	}
	 /*Topic newlyTopic=null;
	 2         QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
	 3         String sql ="select * from topic where type_id= ? order by time desc";
	 4         Object[] params={typeId};
	 5         newlyTopic= runner.query(sql,new BeanHandler<Topic>(Topic.class),params);
	 6         return newlyTopic;*/
	public  List<T> queryForList(String sql,Class<T> cls) throws SQLException{
		List<T> results = this.query.query(conn, sql, new BeanListHandler<T>(cls));    
		return results;
	}
	public  T queryForObject(String sql,Class<T> cls) throws SQLException{
		T t = this.query.query(conn, sql, new BeanHandler<T>(cls));  
		return t;
	}
	public  List<T> queryForMap(String sql,@SuppressWarnings("rawtypes") Class cls) throws SQLException{
		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) this.query.query(conn, sql, new MapListHandler());         
		return results;
	}
	
	public  void insert(){
        String sql ="insert into topic(name,author,content,time,type_id) values(?,?,?,?,?)";
        Object[] params={};
        try {
            //事务开始
            this.query.update(sql,params);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public int update(String sql){
		try {
            //事务开始
           return  this.query.update(conn,sql);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
	}
	public void update(){
        String sql ="update topic set name=? , content=? , time=? where id= ?";
        Object[] params={};
        try {
            //事务开始
            this.query.update(sql,params);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	//添加save方法
    /**
     * 对传递的bean进行分析
     * 将t对象转成insert into users
     */
    public <T> T save(T t) throws Exception{
        //获取类     
        Class<?> cls = t.getClass();
        //从这个字节码上获取name值这个就是表名
        Table table = cls.getAnnotation(Table.class);
        //获取表名 
        String tableName = table.name();
        //组成insert into users(id,name,pwd) values('id',"name','');
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
        update(sql);
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
            return super.update(sql, param);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }

    }

    public int update1(String sql) {
        try {
            return super.update(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    @Override
    protected ResultSet wrap(ResultSet rs) {
        return super.wrap(rs);
    }
}  
