package com.app.webeye;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cyb.UUIDUtils;
import com.cyb.cmd.CmdUtils;
import com.cyb.reflect.DDLUtils;

public class PingTask implements Job{
    public void execute(JobExecutionContext context) throws JobExecutionException{
    	Thread.currentThread().setName(context.getTrigger().getFullName());   
    	NetResult nr;
		String ip = "www.baidu.com";
		for(int i=0;i<10;i++){
			String rs = CmdUtils.exeCMDWithResult("ping "+ip);
			nr = new NetResult();
			nr.setId(UUIDUtils.getUUID());
			nr.setType("ip");
			nr.setIp(ip);
			nr.setDomain(ip);
			nr.setName("百度服务");
			if(rs.contains("找不到主机")){
				nr.setZt("0");
				nr.setDesc("通道异常!");
			}else{
				nr.setZt("1");
				nr.setDesc("通道正常");
			}
			String sql = null;
			try {
				sql = DDLUtils.saveSql(nr);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			try {
				NetDbUtils.dbUtils.save(nr);
				System.out.println(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
    }
}