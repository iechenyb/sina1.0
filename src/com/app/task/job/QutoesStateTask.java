package com.app.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.app.vas.行情状态;

public class QutoesStateTask implements Job{
	public static long count = 0;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	try {
    		行情状态.start();
		} catch (Exception e) {
			e.printStackTrace();
			
		}	
    }
}