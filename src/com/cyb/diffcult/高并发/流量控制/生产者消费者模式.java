package com.cyb.diffcult.高并发.流量控制;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.lhug.data.DataGeneror;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年11月8日 -Xms512m -Xmx1025m -Xmn512m -XX:PermSize=512m
 * -XX:MaxPermSize=512m -XX:MaxMetaspaceSize=512m -Dcom.sun.management.jmxremote
 */
public class 生产者消费者模式 {
	Log log = LogFactory.getLog(生产者消费者模式.class);
	static int bingfarenshu = 20;// 同时计算人数
	static int bigBoucketSize = 30;// 大桶数据量
	static int smallBoucketSize = bigBoucketSize * 3 / 4;
	static Semaphore window = new Semaphore(bingfarenshu);
	final static BlockingQueue<Runnable> BigBucket = new LinkedBlockingQueue<Runnable>(bigBoucketSize);
	final static BlockingQueue<Runnable> SmallBucket = new LinkedBlockingQueue<Runnable>(smallBoucketSize);
	public static boolean has = false;// 线程没有放完

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		Long s = System.currentTimeMillis();
		CalDwjzCustomer customer = new CalDwjzCustomer();
		CalDwjzProductor productor = new CalDwjzProductor();
		ThreadPoolExecutor worker = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		Future<Boolean> rsCustomer = (Future<Boolean>) worker.submit(customer);
		Future<Boolean> rsProductor = (Future<Boolean>) worker.submit(productor);
		if (!rsCustomer.get()) {
			System.out.println("消费线程执行异常！");
		} else {
			System.out.println("消费线程执行成功！");
		}
		if (!rsProductor.get()) {
			System.out.println("投放线程执行异常！");
		}
		{
			System.out.println("投放线程执行成功！");
		}
		Long e = System.currentTimeMillis();
		System.out.println("总共耗时：" + (e - s) / 1000 + "." + (e - s) % 1000);
		worker.shutdown();
		BigBucket.clear();
		SmallBucket.clear();
	}
}

// 累计单位净值计算线程
class CalDwjzTask implements Runnable {
	int idx;
	public CalDwjzTask(int idx) {
		this.idx = idx;
	}
	@Override
	public void run() {
		// 计算出资金账号
		Thread.currentThread().setName("ljdwjz-task-" + idx);
		try {
			生产者消费者模式.window.acquire();
			//Thread.sleep(10);
			生产者消费者模式.window.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

// 消费线程
class CalDwjzProductor implements Callable<Boolean> {
	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("CalDwjzProductor");
		int total = DataGeneror.start + DataGeneror.personNum;
		for (int i = DataGeneror.start; i < total; i++) {
			try {
				CalDwjzTask task = new CalDwjzTask(i);
				生产者消费者模式.BigBucket.put(task);
				System.out.println("大桶线程数：" + 生产者消费者模式.BigBucket.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		生产者消费者模式.has = true;// 所有人员的计算线程投放完成
		return true;
	}
}

// 消费线程
class CalDwjzCustomer implements Callable<Boolean> {
	ThreadPoolExecutor worker = (ThreadPoolExecutor) Executors.newFixedThreadPool(生产者消费者模式.bingfarenshu);
	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("CalDwjzCustomer");
		// 大桶或者小桶任然有数据
		while (!生产者消费者模式.has || !生产者消费者模式.BigBucket.isEmpty()) {
			try {
				System.out.println("小桶线程数：" + 生产者消费者模式.SmallBucket.size());
				// 控制入口，消费者队列最多执行n个
				生产者消费者模式.SmallBucket.put(生产者消费者模式.BigBucket.take());
				worker.execute(生产者消费者模式.SmallBucket.take());
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		worker.shutdown();
		return true;
	}

}
