package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月8日
 */
public class ShutDownMook {
	Log log = LogFactory.getLog(ShutDownMook.class);
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			Runnable r = new Runnable() {
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName()+"启动！");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						
					}
				}
			};
			new Thread(r).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				
			}
		}
		
		Thread demo = new Thread(
				new Runnable() {
					public void run() {
						System.out.println("java 守护线程开启!");
					}
				}
				);
		//demo.setDaemon(true);
		demo.start();
		Runtime.getRuntime().addShutdownHook(new Thread(
				new Runnable() {
					public void run() {
						System.out.println("java虚拟机即将exit!");
					}
				}
		));
	}
}
