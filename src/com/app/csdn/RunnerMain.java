package com.app.csdn;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

import com.app.utils.ParamsUtil;
public class RunnerMain {
	 static Log log = LogFactory.getLog(RunnerMain.class);
	 private static Scheduler sched;
     public static void run() throws Exception{//"*/30 * 8-20 * * ?" "0 */5 * * * ?"
			JobDetail jobDetail = new JobDetail("job","mygroup",GetAricleTimeTask.class);
            CronTrigger trigger = new CronTrigger("trigger","lzstone",ParamsUtil.get("refreshtime"));
            sched = new org.quartz.impl.StdSchedulerFactory().getScheduler();
            
            JobDetail init = new JobDetail("xxx","xxx",InitTask.class);
            CronTrigger initTri = new CronTrigger("yyy","yyy",ParamsUtil.get("recordtime"));
            
            sched.scheduleJob(jobDetail,trigger);
            sched.scheduleJob(init,initTri);
            sched.start();
     }
     //停止
     public static void stop() throws Exception{
            sched.shutdown();
      }
     
     public static void main(String[] args) {
		try {			
			GetAricle.init();
			GetAricle.initProxy();			
			RunnerMain.run();
			GetAricle.recordRank();
			log.info("排名存储路径："+GetAricle.rankFilePath);
			File file = new File(GetAricle.rankFilePath);
			if(!file.exists()){ 
				file.createNewFile();
				log.info("成功创建文件"+file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
//执行
