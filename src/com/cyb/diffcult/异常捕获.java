package com.cyb.diffcult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class 异常捕获 {
	static Log log = LogFactory.getLog(异常捕获.class);
	public static void main (String[] args) throws Exception {
		try{
			m4();
		}catch(Exception e){
			log.info("捕获回调函数抛出的异常信息："+e.toString());
		}
		log.info("------------------");
		m3();
		log.info("------------------");
		m2();
		log.info("------------------");
		m1();
		try{
			log.info("------------------");
			m5();
		}catch(Exception e){
			log.info("捕获回调函数抛出的异常信息："+e.toString());
		}
	}
	public static void m5() throws Exception{
		try{
			try{
				throw new FatherEx();//抛出父类异常
			}catch(ChildEx f){//子类无法获取父类异常
				log.info("catch Father exception !");//不打印
				System.exit(0);
				throw f;
			}
		}catch(ChildEx e){
			log.info("catch Child exception!");
			return ;
		}finally{
			log.info("现场清理over!");//打印
		}
	}
	public static void m4() throws Exception{
		try{
			try{
				throw new FatherEx();//抛出父类异常
			}catch(FatherEx f){//捕获相同类型异常
				log.info("catch Father exception !");//print
				throw f;//抛出异常给父类，不能被childex捕获。
			}
		}catch(ChildEx e){//不能通过子类异常捕获父类异常，不执行
			log.info("catch Child exception!");
			return ;
		}finally{
			log.info("现场清理over!");//print
		}
	}
	public static void m3() throws Exception{
		try{
			try{
				throw new ChildEx();//抛出子类异常
			}catch(FatherEx f){//父类捕获
				log.info("catch Father exception !");//print
				throw f;
			}
		}catch(ChildEx e){
			log.info("catch Child exception!");//print
			return ;
		}finally{
			log.info("现场清理over!");//print
		}
	}
	public static void m1(){
		try{
			int i=0;
			i++;
			log.info(i);
			int b = i/0;
			log.info(b);
			return ;
		}catch(Exception e){
			log.info("出现异常！"+e.getMessage());
			//System.exit(100);//-1 0 1 不执行finally
			return ;//依旧执行清理逻辑
		}finally{
			log.info("清理现场!");
		}
	}
	public static void m2(){
		try{
			int i=0;
			i++;
			log.info(i);
			return ;
		}catch(Exception e){
			log.info(e.getMessage());
		}finally{
			log.info("清理现场!");
		}
	}
	
}
class FatherEx extends Exception{
 private static final long serialVersionUID = 1L;
}
class ChildEx extends FatherEx{
 private static final long serialVersionUID = 1L;
	
}
