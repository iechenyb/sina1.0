package com.app.webeye;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NetTask implements Job{
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	PingTask.execute();
    	TelNetTask.execute();
    	UrlTask.execute();
    }
}