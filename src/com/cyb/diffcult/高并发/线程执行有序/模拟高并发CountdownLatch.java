package com.cyb.diffcult.高并发.线程执行有序;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.date.DateUtil;
import com.cyb.url.UrlUtils;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月21日
 */
public class 模拟高并发CountdownLatch {
	final static int conNums =10*1;
	// 开始的倒数锁
	final static CountDownLatch begin = new CountDownLatch(1);
	// 结束的倒数锁
	final static CountDownLatch end = new CountDownLatch(conNums);
	Log log = LogFactory.getLog(模拟高并发CountdownLatch.class);

	class VisitorWebClient implements Runnable {
		final int No;

		VisitorWebClient(int No) {
			this.No = No;
		}

		public void run() {
			try {
				try {
					// 如果当前计数为零，则此方法立即返回。
					// 等待
					log.info("No." + No + " arrived at "+DateUtil.timeToMilis());
					Thread.currentThread().setName(No+"-User");
					begin.await();//等待开枪示意开始
					Thread.sleep((long) (Math.random() * 1000));
					log.info("No." + No + " run over "+DateUtil.timeToMilis());
					//log.info(UrlUtils.downLoadFromUrl("http://localhost/mvc-1/infor.do", null, null));
				} catch (InterruptedException e) {

				} finally {
					// 每个选手到达终点时，end就减一
					end.countDown();//如果end不减一的话，end.await会一直等待。
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int index = 0; index < conNums; index++) {//准备线程
			final int NO = index + 1;
			VisitorWebClient visitor = new 模拟高并发CountdownLatch().new VisitorWebClient(NO);
			exec.submit(visitor);
		}
		System.out.println("Game Start");
		// begin减一，开始游戏
		boolean start = false;
		if(start){//是否明抢示意开始！
			begin.countDown();//此处注释掉，则所有线程都被阻塞
		}
		// 等待end变为0，即所有选手到达终点
		end.await();
		System.out.println("Game Over");
		exec.shutdown();
	}
}
