package com.app.csdn;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
public class RunnerMain {
	 private static Scheduler sched;
     public static void run() throws Exception{
			JobDetail jobDetail = new JobDetail("job","mygroup",GetAricleTimeTask.class);
            CronTrigger trigger = new CronTrigger("trigger","lzstone","*/10 * 8-20 * * ?");
            sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
            
            /*JobDetail init = new JobDetail("xxx","xxx",InitTask.class);
            CronTrigger initTri = new CronTrigger("yyy","yyy","* *10 8-20 * * ?");*/
            
            sched.scheduleJob(jobDetail,trigger);
            //sched.scheduleJob(init,initTri);
            sched.start();
     }
     //停止
     public static void stop() throws Exception{
            sched.shutdown();
      }
     
     public static void main(String[] args) {
		try {
			GetAricle.init();
			GetAricle.initProxy();
			RunnerMain.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//执行
