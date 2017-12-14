package com.app.webeye;

import com.cyb.UUIDUtils;
import com.cyb.cmd.CmdUtils;
import com.cyb.h2.H2Manager;
import com.cyb.reflect.DDLUtils;

public class NetUtils {
	public static void ping(String cmd) throws Exception{
		
	}
	public static void main(String[] args) throws Exception {
		try {
			H2Manager.start();
			new  NetDbUtils("NetDB");
			NetResult nr;
			String ip = "www.baidu.com";
			for(int i=0;i<10;i++){
				String rs = CmdUtils.exeCMDWithResult("ping "+ip);
				nr = new NetResult();
				nr.setId(UUIDUtils.getUUID());
				nr.setType("ip");
				nr.setIp(ip);
				nr.setName("百度服务");
				if(rs.contains("找不到主机")){
					nr.setZt("0");
					nr.setDesc("通道异常!");
				}else{
					nr.setZt("1");
					nr.setDesc("通道正常");
				}
				String sql = DDLUtils.saveSql(nr);
				NetDbUtils.dbUtils.save(nr);
				System.out.println(sql);
			}
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
