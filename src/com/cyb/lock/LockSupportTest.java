package com.cyb.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月15日
 */
public class LockSupportTest {
	Log log = LogFactory.getLog(LockSupportTest.class);

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		final FIFOMutex mutex=new FIFOMutex();
		Thread A = new Thread(new Runnable() {
			@Override
			public void run() {
				int sum = 0;
				for (int i = 0; i < 10; i++) {
					sum += i;
				}
				LockSupport.park();//让A挂起，不需要在同步代码块里执行
				System.out.println(sum);
				mutex.lock("a");
				mutex.unlock("a");
			}
		});
		A.start();
		LockSupport.unpark(A);//唤醒线程
		// 睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
		// Thread.sleep(1000);
		System.out.println(Thread.currentThread().getName());//主线程
		System.out.println("=================");
		mutex.lock("b");
		mutex.unlock("b");
		CountDownLatch x;
		CyclicBarrier y;
	}
}
