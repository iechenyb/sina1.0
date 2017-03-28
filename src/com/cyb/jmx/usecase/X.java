package com.cyb.jmx.usecase;

import java.io.IOException;
import java.util.Random;


public class X implements XMBean {  
	  
    public static String dir = "D:\\webstorm\\web\\cqcp\\data\\";
    public static String planUrl = "http://www.7879123.com/gw_getplandata.html?r="+new Random().nextDouble();
    public static String zjhUrl = "http://www.7879123.com/cqssc/cqssc.html?r="+new Random().nextDouble();
    public static  boolean isStart;  
    public static Thread  task = null;
    public void stop() {  
    	isStart = false;
    }

	@Override
	public boolean startTask(long sec) {
		System.out.println("start start!");
		task = new Thread(new SSCTask(sec*1000));
		task.start();
		System.out.println("start end!");
		return true;
	}
	class SSCTask implements Runnable{
		long time = 0;
		public SSCTask(long time){
			this.time = time;
		}
		@Override
		public void run() {
			X.isStart = true;
			while(X.isStart){
				try {
					UrlUtils.downLoadFromUrl(planUrl, "plan.txt",dir);
					UrlUtils.downLoadFromUrl(zjhUrl, "zjh.txt",dir);
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
	}
}  
