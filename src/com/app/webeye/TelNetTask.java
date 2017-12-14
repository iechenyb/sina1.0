package com.app.webeye;

import org.quartz.JobExecutionException;

import com.cyb.UUIDUtils;
import com.cyb.cmd.CmdUtils;
import com.cyb.date.DateUtil;
import com.cyb.reflect.DDLUtils;

public class TelNetTask{
    public static void execute() throws JobExecutionException{
    	NetResult nr;
		String ip = "127.0.0.1";
		String port = "8080";
		for(int i=0;i<1;i++){
			String rs = CmdUtils.exeCMDWithResult("telnet "+ip +" "+port);
			nr = new NetResult();
			nr.setId(UUIDUtils.getUUID());
			nr.setType("port");
			nr.setIp(ip);
			nr.setDomain(ip);
			nr.setName("本地服务");
			nr.setTime(DateUtil.timeToSec());
			if(rs.contains("连接失败")){
				nr.setZt("0");
				nr.setDesc("端口异常!");
			}else{
				nr.setZt("1");
				nr.setDesc("端口正常");
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