package com.cyb.fanxing.ex2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.cyb.collection.po.User;
import com.cyb.fanxing.LisiDao;
import com.cyb.fanxing.Person;
import com.cyb.fanxing.ZhangsanDao;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年12月1日
 */
public class AnyTypeClass<T> {
	private Class<T> entityClass;
	
	private T data;
    
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@SuppressWarnings("unchecked")
	public AnyTypeClass() {
		try{
			Type genType = getClass().getGenericSuperclass();
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			entityClass = (Class<T>) params[0];
			System.out.println("泛型的类型："+entityClass);
		}catch(Exception e){}
	}

	public T getVar() {
		try {
			return (T) entityClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	//方法上使用泛型,静态类可以使用任意类型！
	public static <T1> void printInfo1(Person<T1> p) {//定义格式返回值前面，参数里都要有相应的<T>
	    System.out.println(p.getAge());
	}
	//方法上使用泛型,非静态必须使用类声明的泛型
	public  void printInfo2(Person<T> p) {//定义格式返回值前面，参数里都要有相应的<T>
	    System.out.println(p.getAge());
	}
	public static void main(String[] args) {
		AnyTypeClass<User> atc = new AnyTypeClass<User>();
		atc.setData(new User());
		Person<Integer> p = new Person<Integer>();
		p.setAge(1230);
		Person<User> pu = new Person<User>();
		pu.setAge(new User());
		atc.printInfo2(pu);//非静态泛型
		AnyTypeClass.printInfo1(p);//静态泛型
		AnyTypeClass<String> atc_str = new AnyTypeClass<String>();
		atc_str.setData("sfsdfsd");
		
		AnyTypeClass<Map<String,Object>> atc_map = new AnyTypeClass<Map<String,Object>>();
		atc_map.setData(new HashMap<String,Object>());
		
		//System.out.println(new AnyTypeClass<User>().getVar()+","+AnyTypeClass.class.getGenericSuperclass());
		System.out.println( new StringImpl().getVar());
		System.out.println( new UserImpl().getVar());
		System.out.println("**************************");
		ParameterizedType parameterizedType = (ParameterizedType) UserImpl.class.getGenericSuperclass();
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
		for (Type actualTypeArgument : actualTypeArguments) {
			System.out.println(actualTypeArgument);
		}
		System.out.println("**************************");
		parameterizedType = (ParameterizedType) StringImpl.class.getGenericSuperclass();
		actualTypeArguments = parameterizedType.getActualTypeArguments();
		for (Type actualTypeArgument : actualTypeArguments) {
			System.out.println(actualTypeArgument);
		}
		System.out.println("**************************");
		parameterizedType = (ParameterizedType) LisiDao.class.getGenericSuperclass();
		actualTypeArguments = parameterizedType.getActualTypeArguments();
		for (Type actualTypeArgument : actualTypeArguments) {
			System.out.println(actualTypeArgument);
		}
		System.out.println("**************************");
		parameterizedType = (ParameterizedType) ZhangsanDao.class.getGenericSuperclass();
		actualTypeArguments = parameterizedType.getActualTypeArguments();
		for (Type actualTypeArgument : actualTypeArguments) {
			System.out.println(actualTypeArgument);
		}
	}
}
