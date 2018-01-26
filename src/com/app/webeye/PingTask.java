package com.app.webeye;

import com.cyb.UUIDUtils;
import com.cyb.cmd.CmdUtils;
import com.cyb.date.DateUtil;
import com.cyb.reflect.DDLUtils;

public class PingTask{
    public static void execute(){
    	NetResult nr;
		String ip = "www.baidu.com";
		for(int i=0;i<1;i++){
			String rs = CmdUtils.exeCMDWithResult("ping "+ip);
			nr = new NetResult();
			nr.setId(UUIDUtils.getUUID());
			nr.setType("ip");
			nr.setIp(ip);
			nr.setDomain(ip);
			nr.setName("百度服务");
			nr.setTime(DateUtil.timeToSec());
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