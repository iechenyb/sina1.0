package com.cyb.reflect;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月13日
 */
public class DDLUtils {
	Log log = LogFactory.getLog(DDLUtils.class);
	
	
	public static String tableName(Object t) throws IllegalArgumentException, IllegalAccessException{
		Class<?> cls = t.getClass();
		Table table = cls.getAnnotation(Table.class);
		String tableName = table.name();
		return  tableName;
	}
	
	public static String createSql(Object t) throws IllegalArgumentException, IllegalAccessException{
		Class<?> cls = t.getClass();
		Table table = cls.getAnnotation(Table.class);
		String tableName = table.name();
		String sql = "create table " + tableName;
		StringBuffer values = new StringBuffer(sql+"(");
		// 获取所有声明的字段
		Field[] fs = cls.getDeclaredFields();
		// 遍历所有字段
		for (Field f : fs) {
			if (f.isAnnotationPresent(Column.class)) {
				// 获取列名
				String colName = f.getName();
				// 获取column的对象
				Column col = f.getAnnotation(Column.class);
				if (col.name() != null && !col.name().trim().equals("")) {
					colName = col.name();
				}
				String type = col.columnDefinition();
				values.append(colName+" "+type+",");
			}
		}
		String rs = values.substring(0, values.length()-1)+")";
		return rs;
	}
	public static String saveSql(Object t) throws IllegalArgumentException, IllegalAccessException{
		Class<?> cls = t.getClass();
		Table table = cls.getAnnotation(Table.class);
		String tableName = table.name();
		String sql = "insert into " + tableName;
		String cols = "(";
		String values = "values(";
		// 获取所有声明的字段
		Field[] fs = cls.getDeclaredFields();
		// 遍历所有字段
		for (Field f : fs) {
			//if (f.isAnnotationPresent(Column.class)) {
				// 获取列名
				String colName = f.getName();
				// 获取column的对象
				Column col = f.getAnnotation(Column.class);
				if (col.name() != null && !col.name().trim().equals("")) {
					colName = col.name();
				}
				// 获取列值
				f.setAccessible(true);
				// 获取列值
				Object value = f.get(t);
				if (cols.equals("(")) {
					cols += colName;
					if (value instanceof String) {
						values += "'" + value + "'";
					} else {
						values += value;
					}

				} else {
					cols += "," + colName;
					if (value instanceof String) {
						values += ",'" + value + "'";
					} else {
						values += "," + value;
					}
				}
			}
		//}
		cols += ")";
		values += ")";
		sql = sql + cols + " " + values;
		return sql;
	}
	
	public static String updateSql(Object t) throws IllegalArgumentException, IllegalAccessException{
		Class<?> cls = t.getClass();
		Table table = cls.getAnnotation(Table.class);
		String tableName = table.name();
		String sql =  "update " + tableName +" set ";
		StringBuffer values = new StringBuffer("");
		String where =  " where id = '"+getAnnotionedId(t)+"'";
		// 获取所有声明的字段
		Field[] fs = cls.getDeclaredFields();
		// 遍历所有字段
		for (Field f : fs) {
			if (f.isAnnotationPresent(Column.class)) {
				// 获取列名
				String colName = f.getName();
				if(f.isAnnotationPresent(Id.class)){
					continue;
				}
				// 获取column的对象
				Column col = f.getAnnotation(Column.class);
				if (col.name() != null && !col.name().trim().equals("")) {
					colName = col.name();
				}
				// 获取列值
				f.setAccessible(true);
				// 获取列值
				Object value = f.get(t);
				//x=y,z=w
				if(value instanceof String){
					values.append(colName+" = '"+value+"',");
				}else{
					values.append(colName+" = "+value+",");
				}
			}
		}
		sql = sql + values.toString().substring(0, values.toString().length()-1)+ where;
		return sql;
	}
	public static String deleteSql(Object obj){
		Class<?> cls = obj.getClass();
		Table table = cls.getAnnotation(Table.class);
		return "delete from "+table.name()+" where id ='"+getAnnotionedId(obj)+"'";
	}
	
	public static Object getAnnotionedId(Object t){
		Class<? extends Object> c = t.getClass();
		Field[] fs = c.getDeclaredFields();
		for (Field f : fs) {
			try {
				if(f.isAnnotationPresent(Id.class)){
					return f.get(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
