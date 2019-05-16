package com.app.csdn;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cyb.cmd.CmdUtils;
import com.cyb.proxy.http.ProxyUtils;

public class GetAricleTimeTask implements Job{
	static Log log = LogFactory.getLog(GetAricleTimeTask.class);
	public static long count = 0;
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	try {
    		int idx = GetAricle.random(0,GetProxyServer.proxyList.size()-1);
    		if(GetProxyServer.proxyList.get(idx)!=null){
    			log.info("代理："+GetProxyServer.proxyList.get(idx)+" 可用："+
    			CmdUtils.telnet(GetProxyServer.proxyList.get(idx).split("#")[0],Integer.valueOf(GetProxyServer.proxyList.get(idx).split("#")[1])));
    			ProxyUtils.setServiceProxy(GetProxyServer.proxyList.get(idx).split("#")[0], GetProxyServer.proxyList.get(idx).split("#")[1]);
    		}
    		GetAricle.visitorAricleRandom();
			ProxyUtils.removeServiceProxy();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}