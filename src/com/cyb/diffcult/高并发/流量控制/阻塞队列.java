package com.cyb.diffcult.高并发.流量控制;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.lhug.data.DataGeneror;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月11日
 */
public class 阻塞队列 {
	Log log = LogFactory.getLog(阻塞队列.class);
	LinkedBlockingQueue<Runnable> bocket = new LinkedBlockingQueue<Runnable>(10);
	public static int num=0;
	public static void main(String[] args) {
		final Semaphore window = new Semaphore(10);//因为使用了线程池，此时的信号量并没有起到作用。
		ExecutorService work = Executors.newFixedThreadPool(生产者消费者模式.bingfarenshu-15);
		for(int i=0;i<DataGeneror.personNum;i++){
			System.out.println(i);
			Runnable task = new Runnable() {
				@Override
				public void run() {
					try {
						window.acquire();
						byte[] b=new byte[20];//20m*20=400m1024*1024*
						Thread.currentThread().setName("thread-sema"+阻塞队列.num++);
						Thread.sleep(20);
						b=null;
						window.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			work.submit(task);//线程池会很大，容易移除，所以需要放到阻塞队列bocket中
		}
		work.shutdown();
		//线程池本身也会暂用内存空间。
	}
}
