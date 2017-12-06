package com.cyb.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtils {
	public static Object getFieldValueByName(String fieldName, Object o)   
	{      
	   try   
	   {      
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();      
	       String getter = "get" + firstLetter + fieldName.substring(1);  
	       Method method = o.getClass().getMethod(getter, new Class[] {});      
	       Object value = method.invoke(o, new Object[] {});      
	       return value;      
	   } catch (Exception e)   
	   {      
	       System.out.println("属性不存在");      
	       return null;      
	   }      
	}    
	public static void setFieldValueByName(String fieldName,Object val, Object o)   
	{      
	   try   
	   {      
	       String firstLetter = fieldName.substring(0, 1).toUpperCase();      
	       String setter = "set" + firstLetter + fieldName.substring(1); 
	       System.out.println(setter);
	       Method method = o.getClass().getMethod(setter,String.class);      
	       method.invoke(o, new Object[] {val});      
	   } catch (Exception e)   
	   {      
	       System.out.println("属性不存在");      
	   }      
	}
	   
	public static void compareObject(Object bean,Object bean1) throws InvocationTargetException {
		Class<? extends Object> userCla;
		try {
			userCla = bean.getClass();
			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(bean);// 得到此属性的值
				Object val1= f.get(bean1);
				System.out.println(val + "==" + val1+"?"+val.equals(val1));
				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("String")) {
					// System.out.println(f.getType()+"\t是String");
					//f.set(bean, "12"); // 给属性设值
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
					// System.out.println(f.getType()+"\t是int");
					//f.set(bean, 12); // 给属性设值
					Integer.parseInt(String.valueOf(val));
				} else {
					// System.out.println(f.getType()+"\t");
				}
			}
			/*
			 * 得到类中的方法
			 */
			Method[] methods = userCla.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().startsWith("get")) {
					 //System.out.print("methodName:"+method.getName()+"\t");
					 //System.out.println("value:"+method.invoke(bean));//得到get
					// 方法的值
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
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日hj12
	 *@param from vo
	 *@param to po
	 *@throws InvocationTargetException
	 */
	public static void copy(Object from,Object to) throws InvocationTargetException {
		Class<? extends Object> userCla;
		try {
			userCla = from.getClass();
			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = f.get(from);// 得到此属性的值
				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("File")){
				
				}if (type.endsWith("String")) {
					f.set(to, val); // 给属性设值
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
					f.set(to, val); // 给属性设值
				}else{
					
				}
			}
			show(from);
			System.out.println("----------------");
			show(to);
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	public static void show(Object bean) throws InvocationTargetException {
		Class<? extends Object> userCla;
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
					// System.out.println(f.getType()+"\t是String");
					//f.set(bean, "12"); // 给属性设值
				} else if (type.endsWith("int") || type.endsWith("Integer")) {
					// System.out.println(f.getType()+"\t是int");
					//f.set(bean, 12); // 给属性设值
				} else {
					// System.out.println(f.getType()+"\t");
				}
			}
			/*
			 * 得到类中的方法
			 */
			Method[] methods = userCla.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (method.getName().startsWith("get")) {
					 //System.out.print("methodName:"+method.getName()+"\t");
					 //System.out.println("value:"+method.invoke(bean));//得到get
					// 方法的值
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
}