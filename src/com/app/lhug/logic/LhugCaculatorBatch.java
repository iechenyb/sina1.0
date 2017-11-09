package com.app.lhug.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.app.lhug.data.DataGeneror;
import com.app.lhug.po.TB_A;
import com.app.lhug.utils.CalUtils;
import com.cyb.file.FileUtils;


/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年11月8日
 * -Xms512m -Xmx1025m -Xmn512m -XX:PermSize=512m -XX:MaxPermSize=512m 
 * -XX:MaxMetaspaceSize=512m -Dcom.sun.management.jmxremote
 */
public class LhugCaculatorBatch {
	Log log = LogFactory.getLog(LhugCaculatorBatch.class);
	static int bingfarenshu  = 20;//同时计算人数
	static int bigBoucketSize  = 30;//大桶数据量
	static int smallBoucketSize =  bigBoucketSize*3/4;
	static Semaphore window = new Semaphore(bingfarenshu);
	final static BlockingQueue<Runnable> BigBucket = new LinkedBlockingQueue<Runnable>(bigBoucketSize);
	final static BlockingQueue<Runnable> SmallBucket = new LinkedBlockingQueue<Runnable>(smallBoucketSize);
	public static boolean has = false;//线程没有放完
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		File file = new File("d://data//lhug//persons.txt");
		if(file.exists()){file.delete();}//删除历史记录人员信息
		Long s = System.currentTimeMillis();
		CalDwjzCustomer customer = new CalDwjzCustomer();
		CalDwjzProductor productor = new CalDwjzProductor();
		ThreadPoolExecutor worker = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		Future<Boolean> rsCustomer = (Future<Boolean>)worker.submit(customer);
		Future<Boolean> rsProductor = (Future<Boolean>)worker.submit(productor);
		if(!rsCustomer.get()){ 
			System.out.println("消费线程执行异常！");
		}else{
			System.out.println("消费线程执行成功！");
		}
		if(!rsProductor.get()){
			System.out.println("投放线程执行异常！");
		}{
			System.out.println("投放线程执行成功！");
		}
		/*while (true) {  
            if (worker.) {  
            	System.out.println("大赛计算逻辑执行完成！"); 
                break;  
            }  
            Thread.sleep(200);  
        }  */
		Long e = System.currentTimeMillis();
		System.out.println("总共耗时：" + (e - s) / 1000 + "." + (e - s) % 1000);
		worker.shutdown();
		BigBucket.clear();
		SmallBucket.clear();
	}
public static void calAllSeasonLjdwjz(String account) throws IOException{
	calAllSeasonLjdwjzWraper(account, 2);
	calAllSeasonLjdwjzWraper(account, 3);
	calAllSeasonLjdwjzWraper(account, 5);
	calAllSeasonLjdwjzWraper(account, 10);
}
public static void calAllSeasonLjdwjzWraper(String account,int season) throws IOException{
	//long s = System.currentTimeMillis();
	calOneSeasonLjdwjz(account, season,false);
	//long e = System.currentTimeMillis();
	//System.out.println(account+"-"+season+"共耗时：" + (e - s) / 1000 + "." + (e - s) % 1000);

}
public static void calOneSeasonLjdwjz(String account,int season,boolean write) 
		throws IOException{
		String filePath = "D:\\data\\lhug\\rolling\\dhqh\\competitor\\"+account+"\\"+season+"\\a.txt";
		//查询成绩数据list
		List<TB_A> aList = JSONObject.parseArray(FileUtils.readContentFromFile(filePath), TB_A.class);
		int count = aList.size();
		// 计算第一天数据
		{
			TB_A a = aList.get(0);
			// 第一天参赛的成绩进行默认的初始化
			a.setLjdwjz(1);// 默认累计单位净值1
			a.setDrdwjz(1);// 默认当日单位净值1
			a.setDrdwfs(a.getDrqy());// 默认单位出金
			a.setDrljdwcj(0);// 默认单位出金
			a.setDrdwfs(a.getDrqy());
			a.setSrqy(0);// 待定，暂时复制为0
			a.setDrjyk(a.getDrqy() - a.getSrqy() - a.getDrjrj());// 计算当日净盈亏
			// 截至当日累计净入金
			a.setDrljjrj(0 + a.getDrzdjrj());// 计算截至当日累计净入金
			a.setZdljjrj(a.getDrljjrj() < 0 ? 0 : a.getDrljjrj());// 计算当日累计净入金
		}
		for (int i = 1; i < count; i++) {
			TB_A preA = aList.get(i - 1);
			// 获取当日的成绩基础数据
			TB_A curA = aList.get(i);
			curA = CalUtils.calLjdwjz(curA, preA);// 计算累计单位净值并将当日成绩重新赋值
			curA = null;
			preA = null;
		}
		if(write){
			String rs = JSON.toJSONString(aList);
			String dest = "D:\\data\\lhug\\tmp\\"+account+"-"+season+"-a.txt";
			FileUtils.overideString2File(rs, dest);
		}
		aList.clear();
		aList = null;
	}
}
// 累计单位净值计算线程
class CalDwjzTask implements Runnable {
	//ThreadLocal<Integer> idx = new ThreadLocal<Integer>();
	int idx;
	public CalDwjzTask(int idx) {
		this.idx = idx;
	}
	@Override
	public void run() {
		//计算出资金账号
		String account = String.valueOf(idx);
		Thread.currentThread().setName("ljdwjz-task-"+idx);
		//System.out.println("正在生成计算单元，资金账号为："+account);
		try {
			LhugCaculatorBatch.window.acquire();
			LhugCaculatorBatch.calAllSeasonLjdwjz(account);
			FileUtils.appendString2File(account+"\n", "d://data//lhug//persons.txt");
			LhugCaculatorBatch.window.release();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}

}
//消费线程
class CalDwjzProductor implements Callable<Boolean> {
	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("CalDwjzProductor");
		int total  = DataGeneror.start+DataGeneror.personNum;
		for (int i = DataGeneror.start; i < total ; i++) {
			try {
				//System.out.println("开始生产序号=" + i);
				CalDwjzTask task = new CalDwjzTask(i);
				LhugCaculatorBatch.BigBucket.put(task);
				System.out.println("大桶线程数："+LhugCaculatorBatch.BigBucket.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
	    }
		LhugCaculatorBatch.has = true;//所有人员的计算线程投放完成
		return true;
	}
}


// 消费线程
class CalDwjzCustomer implements Callable<Boolean> {
	ThreadPoolExecutor worker = (ThreadPoolExecutor) Executors.newFixedThreadPool(LhugCaculatorBatch.bingfarenshu);
	@Override
	public Boolean call() throws Exception {
		Thread.currentThread().setName("CalDwjzCustomer");
		//大桶或者小桶任然有数据
		while(!LhugCaculatorBatch.has||!LhugCaculatorBatch.BigBucket.isEmpty()){
	    	try {
	    		System.out.println("小桶线程数："+LhugCaculatorBatch.SmallBucket.size());
	    		//控制入口，消费者队列最多执行n个
		    	LhugCaculatorBatch.SmallBucket.put(LhugCaculatorBatch.BigBucket.take());
		    	worker.execute(LhugCaculatorBatch.SmallBucket.take());
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
	      }
		worker.shutdown();
		return true;
	}

}
