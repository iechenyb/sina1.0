package com.cyb.lock;

import java.util.concurrent.CyclicBarrier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月15日
 */
public class CyclicBarrierTest {
	Log log = LogFactory.getLog(CyclicBarrierTest.class);
	static CyclicBarrier c = new CyclicBarrier(2);// 主线程和子线程启动成功均执行
	/*CyclicBarrier(int parties, Runnable barrierAction)
	优先执行barrierAction*/
	static CyclicBarrier c1 = new CyclicBarrier(2, new A());

	public static void method1() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {

				}
				System.out.println(1);
			}
		}).start();

		try {
			c.await();
		} catch (Exception e) {

		}
		System.out.println(2);
	}

	// 可循环使用（Cyclic）的屏障（Barrier）
	public static void main(String[] args) {
		// method1();
		method2();
	}

	private static void method2() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					c1.await();
				} catch (Exception e) {

				}
				System.out.println(1);
			}
		}).start();

		try {
			c1.await();
		} catch (Exception e) {

		}
		System.out.println(2);
	}

	static class A implements Runnable {
		@Override
		public void run() {
			System.out.println(3);
		}

	}
}
