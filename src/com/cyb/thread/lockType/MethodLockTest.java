package com.cyb.thread.lockType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.thread.锁类型;
/**
 *作者 : iechenyb<br>
 *类描述: 需要特别说明：对于同一个类A，线程1争夺A对象实例的对象锁，线程2争夺类A的类锁，这两者不存在竞争关系。也就说对象锁和类锁互补干预内政
  静态方法则一定会同步，非静态方法需在单例模式才生效，但是也不能都用静态同步方法，总之用得不好可能会给性能带来极大的影响。另外，有必要说一下的是Spring的bean默认是单例的。<br>
 *创建时间: 2018年2月9日
 */
class ThreadA extends Thread {
    @Override
    public void run() {
    	Thread.currentThread().setName("A"+System.currentTimeMillis());
    	new 锁类型().objLockMethod1();
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
    	Thread.currentThread().setName("B"+System.currentTimeMillis());
    	new 锁类型().objLockMethod2();
    }
}
class ThreadC extends Thread {
	锁类型 t;
	public ThreadC(锁类型 t){
		this.t=t;
	}
    @Override
    public void run() {
    	Thread.currentThread().setName("C"+System.currentTimeMillis());
    	t.objLockMethod1();
    	//t.objLockObjectMethod2();object锁与方法所在的对象锁不是同一个锁，可以并行
    }
}

class ThreadD extends Thread {
	锁类型 t;
	public ThreadD(锁类型 t){
		this.t=t;
	}
    @Override
    public void run() {
    	Thread.currentThread().setName("D"+System.currentTimeMillis());
    	t.objLockMethod1();
    	//t.objLockObjectMethod2();
    }
}
class ThreadD1 extends Thread {
	锁类型 t;
	public ThreadD1(锁类型 t){
		this.t=t;
	}
    @Override
    public void run() {
    	Thread.currentThread().setName("D1-"+System.currentTimeMillis());
    	t.objLockMethod2();
    	//t.objLockObjectMethod2();
    }
}
class ThreadE extends Thread {
    @Override
    public void run() {
    	 //锁类型.classLock1();
    	Thread.currentThread().setName("E"+System.currentTimeMillis());
    	new 锁类型().classLock2();
    	
    }
}

class ThreadF extends Thread {
    @Override
    public void run() {
    	Thread.currentThread().setName("F"+System.currentTimeMillis());
    	锁类型.classLock1();
    	//new 锁类型().classLock2();
    }
}
public class MethodLockTest {
	Log log = LogFactory.getLog(MethodLockTest.class);
	public static void main(String[] args) {
		new ThreadA().start();
		new ThreadB().start();//两个不同对象的锁，同时执行
		锁类型 t = new 锁类型();
		new ThreadC(t).start();
		new ThreadD(t).start();//同一个对象锁，不可以同时执行
		new ThreadD1(t).start();//c d d1 证明一个对象只有一个锁
		
		new ThreadE().start();//e f 证明静态方法在类上枷锁,类锁也只有一个，不能有两个方法同时获取类锁
		new ThreadF().start();
	}
}
