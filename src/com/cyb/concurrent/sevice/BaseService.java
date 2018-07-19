package com.cyb.concurrent.sevice;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年7月6日
 */
public class BaseService {
	Log log = LogFactory.getLog(BaseService.class);
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public String dataInCache = "";// 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	public AtomicInteger count = new AtomicInteger(0);
	protected static AtomicInteger objCount = new AtomicInteger(0);

	// 模拟从缓存中读取数据
	public void getDataFromCacheSafe() {
		rwl.readLock().lock();// 上读锁，其他线程只能读不能写
		// System.out.println(Thread.currentThread().getName() + " be ready to
		// read data!");
		try {
			if ("".equals(dataInCache)) {// 缓存没有数据，重新写
				// rwl.writeLock().lock();
				System.out.println("没有数据，开始写数据了！！！");
				dataInCache = "iechenyb";
				count.incrementAndGet();
				if (count.get() > 1) {
					throw new Exception("发现多次写操作！！！" + count.get());
				}
				// rwl.writeLock().unlock();
				System.out.println(Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 写数据......");
			} else {// 否则直接返回缓存的数据
				System.out.println(
						Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 读数据：" + dataInCache);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(9);
		}
		rwl.readLock().unlock(); // 释放读锁，最好放在finnaly里面
	}

	public void getDataFromCacheUnSafe() {
		try {
			if ("".equals(dataInCache)) {
				System.out.println("没有数据，开始写数据了！！！");
				dataInCache = "iechenyb";
				count.incrementAndGet();
				if (count.get() > 1) {
					throw new Exception("发现多次写操作！！！" + count.get());
				}
				System.out.println(Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 写数据......");
			} else {
				System.out.println(
						Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 读数据：" + dataInCache);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(9);
		}
	}

	// 模拟从缓存中读取数据
	public void getDataFromCacheSafeRW() {
		try {
			if ("".equals(dataInCache)) {// 缓存没有数据，重新写
				rwl.writeLock().lock();
				System.out.println("没有数据，开始写数据了！！！");
				dataInCache = "iechenyb";
				count.incrementAndGet();
				if (count.get() > 1) {
					throw new Exception("发现多次写操作！！！" + count.get());
				}
				rwl.writeLock().unlock();
				System.out.println(Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 写数据......");
			} else {// 否则直接返回缓存的数据
				rwl.readLock().lock();// 上读锁，其他线程只能读不能写
				System.out.println(
						Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 读数据：" + dataInCache);
				rwl.readLock().unlock(); // 释放读锁，最好放在finnaly里面
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(9);
		}
	}

	private Lock lock =  new ReentrantLock();
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 线程安全<br>
	 *创建时间: 2017年7月15日
	 *@throws Exception
	 */
	public void reentrantLock() throws Exception {
		try {
			// 加锁
			lock.lock();
			if ("".equals(dataInCache)) {
				count.incrementAndGet();
				dataInCache = "iechenyb";
				if (count.get() > 1) {
					throw new Exception("发现多次写操作！！！" + count.get());
				}
				System.out.println(Thread.currentThread().getName() + "正在写数据...");
			}else{
				System.out.println(
						Thread.currentThread().getName() + "-" + System.currentTimeMillis() + " 读数据：" + dataInCache);
			}
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
}
