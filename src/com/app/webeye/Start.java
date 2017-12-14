package com.app.webeye;
import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.cyb.h2.H2Manager;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月13日
 */
public class Start {
	private static Scheduler sched;
	Log log = LogFactory.getLog(Start.class);
	public static void main(String[] args) {
		JobDetail jobDetail = new JobDetail("job","default",NetTask.class);
        //目标 创建任务计划
        try {
        	H2Manager.start();
			new  NetDbUtils("NetDB");
			CronTrigger trigger = new CronTrigger("trigger","ping","*/10 * * * * ?");
			 try {
				sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
				sched.start();
			} catch (SchedulerException e1) {
				e1.printStackTrace();
			}
	         try {
				sched.scheduleJob(jobDetail,trigger);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
	         while(true){
	    			try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	         }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
