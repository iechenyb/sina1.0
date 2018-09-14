package com.app.h2Study;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class H2LogTask implements Job{
	public static long count = 0;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	try {
			H2LogTestMain.enter();
		} catch (Exception e) {
			e.printStackTrace();
			
		}	
    }
}