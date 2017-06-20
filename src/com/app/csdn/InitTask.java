package com.app.csdn;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class InitTask implements Job{
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	try {
    		//GetAricle.init();
    		//GetAricle.initProxy();
    		System.out.println(GetAricle.recordRank());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}