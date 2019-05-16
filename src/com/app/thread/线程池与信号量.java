package com.app.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年12月6日
 * https://blog.csdn.net/mryang125/article/details/81490783
 */

public class 线程池与信号量 {
	Log log = LogFactory.getLog(线程池与信号量.class);
	public static void main(String[] args) {
		//testSeamphore();
		//testMutex();
		testPool();
	}

	public static void testSeamphore() {
		final Semaphore semaphore = new Semaphore(2);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread() {
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + " start running **********************");
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + " stop running  ----------------------");
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			thread.start();
		}
	}

	public static void testPool() {
		ExecutorService executorService 
		= new ThreadPoolExecutor(2, 5, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
		
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread() {
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " start running **********************");
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + " stop running  ----------------------");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			executorService.submit(thread);
		}
		executorService.shutdown();
	}
	/**
	 * Semaphore实现互斥锁的方式是使用初始值为1的Semaphore对象，
	 * 这样每条线程获取许可后必须释放许可，
	 * 其它线程才能获取许可，当前拥有许可的线程就拥有了互斥锁。
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日
	 */
	public static void testMutex() {
		final Semaphore semaphore = new Semaphore(1);
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					try {
						semaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "已获得许可");
						Thread.sleep(2000);
						System.out.println(Thread.currentThread().getName() + "已释放许可");
						semaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	
	public static void testCallablePool() throws Exception, ExecutionException {
		 
		List<Future<String>> futures = new ArrayList<Future<String>>();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 5; i++) {
			Future<String> future = 
					newFixedThreadPool.submit(new TaskCallable(i));
			futures.add(future);
		}
 
		// 打印结果
		for (Future<String> f : futures) {
			boolean done = f.isDone();
			 // 从结果的打印顺序可以看到，即使未完成，也会阻塞等待
			System.out.println(done ? "已完成" : "未完成");
			//从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
			System.out.println("已完成线程返回future结果： " + f.get());
		}
 
		newFixedThreadPool.shutdown();
	}
}
class TaskCallable implements Callable<String>{
	
	/**线程编号*/
	private int tNo;
	
	public TaskCallable(int tNo){
		this.tNo=tNo;
	}
	
	public String call() throws Exception {
		
		String tName=Thread.currentThread().getName();
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(tNo+"-"+tName+" start time is："+currentTimeMillis/1000);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(tNo+"-"+tName+ " is working...");
		
		return "the thread is "+tNo;
	}
	
}
