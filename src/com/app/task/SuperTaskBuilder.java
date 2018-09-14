package com.app.task;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

import com.app.h2Study.H2LogTask;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月21日
 */
public class SuperTaskBuilder {
	static long taskSeq=0;
	static long triggerSeq=0;
	static long jobSeq=0;
	static long groupSeq=0;
	Class<?> target = null;
	String corn = null;// 

	public SuperTaskBuilder target(Class<?> t) {
		target = t;
		return this;
	}

	public SuperTaskBuilder corn(String c) {
		corn = c;
		return this;
	}

	public static class Builder {
		private Class<?> target;
		private String corn;

		public Builder target(Class<?> t) {
			this.target = t;
			return this;
		}

		public Builder corn(String c) {
			this.corn = c;
			return this;
		}

		public SuperTaskBuilder builder() throws Exception  {
			return new SuperTaskBuilder(this);
		}
	}
	public void builderTask() throws Exception  {
		if(target==null) throw new Exception("任务target不能为空！");
		if(corn==null) throw new Exception("执行周期参数corn不能为空！");
		Scheduler sched = null;
		JobDetail jobDetail = new JobDetail("job"+jobSeq++, "mygroup"+groupSeq++, target);
		CronTrigger trigger = new CronTrigger("trigger"+triggerSeq++, "task"+taskSeq++, corn);
		sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
		sched.scheduleJob(jobDetail, trigger);
		sched.start();
	}
	private SuperTaskBuilder(Builder b) throws Exception {
		target = b.target;
		corn = b.corn;
		builderTask();
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		SuperTaskBuilder params = 
				new SuperTaskBuilder
				.Builder()//静态类
                .target(H2LogTask.class)
                .corn("0 0 * * * ?")
                .builder();
	}

}
