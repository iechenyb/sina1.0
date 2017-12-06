package com.cyb.diffcult.高并发.线程执行有序;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年11月11日
 */
public class ThreadTwo  implements Runnable{
	Log log = LogFactory.getLog(ThreadTwo.class);
	 @Override
	    public void run() {
	        String threadName = Thread.currentThread().getName();
	        System.out.println(threadName + " start...");
	        System.out.println(threadName + " end.");
	    }
}
