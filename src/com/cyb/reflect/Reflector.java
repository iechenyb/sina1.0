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
		Object val = null;
		for (Field f : fs) {
			try {
				if(f.isAnnotationPresent(Id.class)){
					val =  f.get(t);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return val;
	}
}
