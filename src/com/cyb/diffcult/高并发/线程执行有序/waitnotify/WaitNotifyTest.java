package com.cyb.diffcult.高并发.线程执行有序.waitnotify;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年11月11日
 */
public class WaitNotifyTest {
	Log log = LogFactory.getLog(WaitNotifyTest.class);
	public static void main(String[] args) {
		Message msg = new Message("process it");
		Waiter waiter = new Waiter(msg);
		new Thread(waiter, "waiter").start();

		Waiter waiter1 = new Waiter(msg);
		new Thread(waiter1, "waiter1").start();

		Notifier notifier = new Notifier(msg);
		new Thread(notifier, "notifier").start();
		System.out.println("All the threads are started");
	}
}
