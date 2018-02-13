package com.cyb.thread;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月9日
 */
class MyThread extends Thread {
	public void run() {
		System.out.println("开始执行！");
	}
}

class MyThreadWithExceptionTry extends Thread {
	public void run() {
		try {
			System.out.println("开始执行！" + (1 / 0));
		} catch (Exception e) {
			System.out.println("发现异常！" + e.toString());
			// e.printStackTrace();
		}
	}
}

class MyThreadWithException extends Thread {
	public void run() {
		System.out.println("开始执行！" + (1 / 0));
	}
}

class ExceptionHandler implements UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Exception: " + e.getMessage());
	}
}

public class 异常捕获 {
	Log log = LogFactory.getLog(异常捕获.class);

	public static void main(String[] args) {
		MyThread my = new MyThread();
		my.start();
		new MyThreadWithExceptionTry().start();// 无法捕获异常
		MyThreadWithException t = new MyThreadWithException();
		t.setUncaughtExceptionHandler(new ExceptionHandler());
		t.start();
	}
}
