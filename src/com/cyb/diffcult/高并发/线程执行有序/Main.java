package com.cyb.diffcult.高并发.线程执行有序;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月11日
 */
public class Main {
	Log log = LogFactory.getLog(Main.class);
	public static void main(String[] args) {
		String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start...");

        Thread firstThread = new Thread(new ThreadOne());
        Thread secondThread = new Thread(new ThreadTwo());
        Thread thirdThread = new Thread(new ThreadThree());
    
        // 2. thread run in order
        try {
            firstThread.start();
            firstThread.join();
            secondThread.start();
            secondThread.join();
            thirdThread.start();
            thirdThread.join();
        } catch (Exception ex) {
            System.out.println("thread join exception.");
        }
        System.out.println(threadName + " end.");
	}
}
