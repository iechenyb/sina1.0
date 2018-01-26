package com.cyb.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月26日
 */
public class TestMyException {
	Log log = LogFactory.getLog(TestMyException.class);

	public static int calDiv(int a, int b) throws MyException {

		if (b == 0) {
			throw new MyException("除数不能为0！");
		}
		int i = a / b;
		return i;
	}

	public static void main(String[] args) {
		try {
			calDiv(1, 0);
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
		try {
			calDiv(1, 0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			calDiv(1, 0);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}

		try {
			calDiv(1, 0);
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

	// catch中的异常被抑制
	@SuppressWarnings("finally")
	public static int foo() throws Exception {
		try {
			@SuppressWarnings("unused")
			int a = 5 / 0;
			return 1;
		} catch (ArithmeticException amExp) {
			throw new Exception("我将被忽略，因为下面的finally中使用了return");
		} finally {
			return 100;
		}
	}

	// try中的异常被抑制
	@SuppressWarnings({ "finally", "unused" })
	public static int bar() throws Exception {
		try {
			int a = 5 / 0;
			return 1;
		} finally {
			return 100;
		}
	}

	// catch中的异常被抑制
	@SuppressWarnings({ "finally", "unused" })
	public static int foo1() throws Exception {
		try {
			int a = 5 / 0;
			return 1;
		} catch (ArithmeticException amExp) {
			throw new Exception("我将被忽略，因为下面的finally中抛出了新的异常");
		} finally {
			throw new Exception("我是finaly中的Exception");
		}
	}

	// try中的异常被抑制
	@SuppressWarnings({ "finally", "unused" })
	public static int bar1() throws Exception {
		try {
			int a = 5 / 0;
			return 1;
		} finally {
			throw new Exception("我是finaly中的Exception");
		}
	}
}
