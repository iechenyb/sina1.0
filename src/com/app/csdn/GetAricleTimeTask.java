package com.app.csdn;

import java.io.IOException;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cyb.date.DateUtil;
import com.cyb.proxy.http.ProxyUtils;

public class GetAricleTimeTask implements Job{
	public static long count = 0;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	try {
    		int idx = GetAricle.random(1,GetAricle.proxys.keySet().size());
    		if(GetAricle.proxys.get(idx)!=null){
    			System.out.println(DateUtil.timeToMilis(new Date())+" "+"使用代理："+GetAricle.proxys.get(idx));
    			ProxyUtils.setServiceProxy(GetAricle.proxys.get(idx).split("#")[0], GetAricle.proxys.get(idx).split("#")[1]);
    		}
    		GetAricle.visitorAricle();
			ProxyUtils.removeServiceProxy();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}