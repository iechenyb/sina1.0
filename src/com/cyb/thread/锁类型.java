package com.cyb.thread;

//http://blog.csdn.net/u013142781/article/details/51697672
public class 锁类型 {

	public Object object = new Object();

	// 对象锁：形式1
	public synchronized void objLockMethod1() {
		System.out.println(Thread.currentThread().getName() + " in...objLockMethod1");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " out...objLockMethod1");
	}

	// 对象锁：形式2
	public void objLockMethod2() {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " in...objLockMethod2");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " out...objLockMethod2");
		}

	}

	// 对象锁：形式2
	public void objLockObjectMethod2() {
		synchronized (object) {
			System.out.println(Thread.currentThread().getName() + " in...objLockObjectMethod2");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " out...objLockObjectMethod2");
		}

	}

	// 类锁：形式1
	public static synchronized void classLock1() {
		System.out.println(Thread.currentThread().getName() + " classLock1------in");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " classLock1------out");
	}

	// 类锁：形式2
	public void classLock2() {
		synchronized (锁类型.class) {
			System.out.println(Thread.currentThread().getName() + " classLock2------in");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " classLock2------out");
		}
	}
}
