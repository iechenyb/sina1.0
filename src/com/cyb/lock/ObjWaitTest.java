package com.cyb.lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月15日
 */
public class ObjWaitTest {
	Log log = LogFactory.getLog(ObjWaitTest.class);

	public static void main(String[] args) throws Exception {
		final Object obj = new Object();
		Thread A = new Thread(new Runnable() {
			@Override
			public void run() {
				int sum = 0;
				for (int i = 0; i < 10; i++) {
					sum += i;
				}
				try {
					synchronized (obj) {
						obj.wait();//必须用在同步代码块里边
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(sum);
			}
		});
		A.start();
		// 睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
		// Thread.sleep(1000);
		synchronized (obj) {
			obj.notify();//必须用在同步代码块里边
		}
	}
}
/**
 * 多运行几次上边的代码，有的时候能够正常打印结果并退出程序，
 * 但有的时候线程无法打印结果阻塞住了。
 * 原因就在于：主线程调用完notify后，
 * 线程A才进入wait方法，导致线程A一直阻塞住。由于线程A不是后台线程，所以整个程序无法退出。
 */
