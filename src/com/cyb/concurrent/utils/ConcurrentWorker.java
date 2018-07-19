package com.cyb.concurrent.utils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.concurrent.sevice.MySingleton2;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年7月6日
 */
public class ConcurrentWorker {
	Log log = LogFactory.getLog(ConcurrentWorker.class);
	/**
     * 模拟client_num个客户端同时访问
     */
    private static int clientNum = 1000;
    private static int threadsNum = 1000;
    /**
     * 计数器
     */
    final static CountDownLatch doneSignal = new CountDownLatch(clientNum);
    public static void main(String[] args) {
        /**
         * 建立线程池
         */
        ExecutorService exec = Executors.newFixedThreadPool(threadsNum);
        for(int i=0 ; i< clientNum; i++){
             MyRunnable myRunnable = new MyRunnable();
             exec.execute(myRunnable);
             doneSignal.countDown();
         }
    }
 
     static class  MyRunnable implements  Runnable{
        @Override
        public void run() {
            try{
                doneSignal.await();
            	//MySingleton.getInstance();//不安全
            	//MySingleton2.getInstance();//安全
				//MySingleton2.getInstance().getDataFromCacheSafe();
				//MySingleton2.getInstance().getDataFromCacheUnSafe();
                //MySingleton2.getInstance().getDataFromCacheSafeRW();
                MySingleton2.getInstance().reentrantLock();//可行
			} catch (Exception e) {
				e.printStackTrace();
				
			}//模拟并发读取数据
            //查看单例是否在并发状态下创建成功！
            //System.out.println(MySingleton2.getInstance()+"=");
        }
     }
}
