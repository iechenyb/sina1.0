package com.app.task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月21日
 */
public class SuperTaskFactory2 {
	Log log = LogFactory.getLog(SuperTaskFactory2.class);
	static long taskSeq=0;
	static long triggerSeq=0;
	static long jobSeq=0;
	static long groupSeq=0;
	static Class<?> target=null;
	static String corn=null;//"*/10 * * * * ?"
	
	public  static void target(Class<?> t){
		target = t;
	}
	public static  void corn(String c){
		corn = c;
	}
	public static void build() throws Exception{
		if(target==null) throw new Exception("任务target不能为空！");
		if(corn==null) throw new Exception("执行周期参数corn不能为空！");
		Scheduler sched = null;
		JobDetail jobDetail = new JobDetail("job"+jobSeq++, "mygroup"+groupSeq++, target);
		CronTrigger trigger = new CronTrigger("trigger"+triggerSeq++, "task"+taskSeq++, corn);
		sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
		sched.scheduleJob(jobDetail, trigger);
		sched.start();
	} 
}
