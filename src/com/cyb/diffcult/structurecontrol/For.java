package com.cyb.diffcult.structurecontrol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class For {
	static Log log = LogFactory.getLog(For.class);
	static int count = 0;
	public static void main(String[] args) {
		for(int i=init();i<max();i++,step(i)){
			log.info("执行{} "+i);
		}
	}
	public static int init(){
		log.info("初始化变量！");
		return 0;
	}
	public static int max(){
		log.info("最大值判断！");
		return 3;
	}
	public static int step(int i){
		log.info("步长+1！");
		return i;
	}
}
/**
 * 2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.init:18[main] 初始化变量！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.max:22[main] 最大值判断！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.main:14[main] 执行{} 0
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.step:26[main] 步长+1！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.max:22[main] 最大值判断！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.main:14[main] 执行{} 1
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.step:26[main] 步长+1！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.max:22[main] 最大值判断！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.main:14[main] 执行{} 2
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.step:26[main] 步长+1！
2017-08-31 21:32:27,100 com.cyb.diffcult.structurecontrol.For.max:22[main] 最大值判断！
*/
