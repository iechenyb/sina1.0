package com.app.vas;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.task.SuperTaskBuilder;
import com.app.task.job.QutoesStateTask;
import com.cyb.file.FileUtils;
import com.cyb.url.fina.FinalHttpClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月21日
 */
public class 行情状态 {
	Log log = LogFactory.getLog(行情状态.class);
	public static void start(){
		String statStr = FinalHttpClient.doGet("http://211.95.44.232:8080/VASDataCenter/qutoes/infor.html");
		System.out.println(statStr);
		FileUtils.appendString2File(statStr+"\n", "d:/logs/qutoesstat.log");
	}
	public static void main(String[] args) throws Exception {
				new SuperTaskBuilder
				.Builder()//静态类
                .target(QutoesStateTask.class)
                .corn("0 * * * * ?")
                .builder();
	}
}
