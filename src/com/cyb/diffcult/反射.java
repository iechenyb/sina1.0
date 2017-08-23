package com.cyb.diffcult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class 反射 {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		String str = "chenyb";
		Method m = str.getClass().getMethod("toUpperCase");//获取工具包内类的方法
		System.out.println(m.invoke(str));
		Goto to = new Goto();
		Method m1 = to.getClass().getMethod("print");//获取无参数的方法
		m1.invoke(to);
		Method m2 = to.getClass().getMethod("print", String.class);//获取带参数的方法
		m2.invoke(to, "iechenyb");
	}
}
