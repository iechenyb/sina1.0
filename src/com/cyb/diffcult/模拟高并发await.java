package com.cyb.diffcult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.date.DateUtil;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月21日
 */
public class 模拟高并发await {
	Log log = LogFactory.getLog(模拟高并发await.class);
	final static int conNums =10*1;
	class VisitorWebClient implements Runnable {
		final Object lock;
        final int No;
		VisitorWebClient(Object lock,int No) {
			this.lock = lock;
			this.No = No;
		}

		public void run() {
			try {
				log.info("No." + No + " arrived at "+DateUtil.timeToMilis());	
				lock.wait();
				//synchronized (lock) {}
					log.info("No." + No + " execute at "+DateUtil.timeToMilis());	
				
				//lock.notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		final Object lock = new Object();
		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int index = 0; index < conNums; index++) {//准备线程
			final int NO = index + 1;
			VisitorWebClient visitor = new 模拟高并发await().new VisitorWebClient(lock,NO);
			exec.submit(visitor);
		}
		System.out.println("Game Start");
		//lock.notifyAll();
		System.out.println("Game Over");
		exec.shutdown();
	}
}
