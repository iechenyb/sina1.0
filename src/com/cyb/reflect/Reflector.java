package com.cyb.reflect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

public class Reflector {
	public void printNameValueOfObject(Reflector bean) {
		Class<? extends Reflector> userCla;
		try {
			userCla = bean.getClass();
			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(bean);// 得到此属性的值
				System.out.println(f.getName() + "= " + val);
				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("String")) {
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
				} else {
				}
			}
			Method[] methods = userCla.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().startsWith("get")) {
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.println("属性不存在");
			return null;
		}
	}

	public static Map<String, Object> object2Map(Object t) {
		Map<String, Object> bean = new HashMap<String, Object>();
		Class<? extends Object> c = t.getClass();
		Field[] fs = c.getDeclaredFields();
		for (Field f : fs) {
			try {
				bean.put(f.getName(), f.get(t));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
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
}
