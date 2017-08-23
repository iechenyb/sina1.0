package com.cyb.thread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月23日
 */
public class VolatileTest {
	public static volatile Long account = 0l ;
	static Log log = LogFactory.getLog(VolatileTest.class);
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new Thread(new AddThread()).start();
		}
		try {
			Thread.sleep(5*1000);
			log.info("预期值1000,实际值"+account);
			//2017-08-23 20:54:55,866 com.cyb.thread.VolatileTest.main:18[main] 预期值1000,实际值895
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//休息10秒
	}
}
class AddThread implements Runnable {//synchronized可以保证结果正确。
	/*public synchronized void run() {
		for(int i=1;i<=100;i++){
			VolatileTest.account++;
		}
	}*/
	Object obj = new Object();
	public  void run() {
		synchronized (VolatileTest.class) {//VolatileTest.class obj 可以保证正确结果
			for(int i=1;i<=100;i++){
				VolatileTest.account++;
			}
		}
	}
} 
