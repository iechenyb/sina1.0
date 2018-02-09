package com.cyb.fanxing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月31日
 */
public class Person<T> {// 要使用泛型的类加上类似与通配符的<T>,中间字符可以任意

	// 也可以传入多个类型<T,N>

	private T age;// 内部类型,使用T代替

	public void setAge(T age) {// 内部类型,使用T代替

		this.age = age;

	}

	public T getAge() {// 内部类型,使用T代替

		return this.age;

	}

}