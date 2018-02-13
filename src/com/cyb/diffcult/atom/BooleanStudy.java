package com.cyb.diffcult.atom;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月12日
 */
class BarWorker implements Runnable {

	private static AtomicBoolean exists = new AtomicBoolean(false);
	private static boolean exists1 = false;
	private String name;

	public BarWorker(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		run1();//总能稳定的工作
		//run2();//工作不稳定

	}
	public void run2(){
		if (!exists1) {//exists.compareAndSet(false, true)
			System.out.println(name + " enter");
			try {
				exists1 = true;
				System.out.println(name + " working");
				TimeUnit.SECONDS.sleep(2);
				exists1 = false;
			} catch (InterruptedException e) {
				// do nothing
			}
			System.out.println(name + " leave");
		} else {
			System.out.println(name + " give up");
		}
	}
	public void run1() {
		if (exists.compareAndSet(false, true)) {//如果等于false则设置成true
			System.out.println(name + " enter");
			try {
				System.out.println(name + " working");
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// do nothing
			}
			System.out.println(name + " leave");
			exists.set(false);
		} else {
			System.out.println(name + " give up");
		}

	}
}

public class BooleanStudy {
	Log log = LogFactory.getLog(BooleanStudy.class);

	public static void main(String[] args) {
		//测试目的，同一个任务，只能有一个人员做！
		BarWorker bar1 = new BarWorker("bar1");
		BarWorker bar2 = new BarWorker("bar2");
		new Thread(bar1).start();
		new Thread(bar2).start();
	}
}
