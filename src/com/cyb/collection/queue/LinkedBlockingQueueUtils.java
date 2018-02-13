package com.cyb.collection.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*阻塞队列 实现是线程安全的，实现了先进先出等特性，是作为生产者消费者的首选
可以指定容量，也可以不指定，不指定的话，默认最大是Integer.MAX_VALUE，其中主要用到put和take方法，
put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。*/
public class LinkedBlockingQueueUtils {
	public static void main(String[] args) throws InterruptedException {
		testPull();
		//testPut();
	}
	public static void testPull() throws InterruptedException{
		BlockingQueue<Object> x = new LinkedBlockingQueue<Object>(5);
		System.out.println(x.size());
		try {
			for(int i=0;i<8;i++){
			//x.add("aaa");//报queue full 异常 返回true|false
			//x.put("bbb");//阻塞线程，停止放入对象,void
				System.out.println(x.offer("ccc"+i));//返回放入成功或者失败标志true|false
				System.out.println(x.size()+"==>"+x);
			}
			for(int i=0;i<8;i++){
				//x.peek();//不阻塞 弹出不移除队列头的元素，队列为空是，返回null
				//x.poll();//不阻塞 移除队列头元素，队列为空，则返回null
				x.take();//阻塞 获取并移除队列头元素，空时，等待直到有可用元素
				//x.remove("ccc");
				System.out.println(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		/*x.add("aaa");
		x.put("bbb");
		x.offer("ccc");
		System.out.println(x);
		x.peek();
		System.out.println(x);
		x.poll();
		System.out.println(x);
		x.remove("ccc");
		System.out.println(x);
		System.out.println(x.take());
		System.out.println("剩余记录："+x);
		System.out.println("get:"+x.take());//阻塞*/
	}
	public static void testPut(){
		BlockingQueue<Object> x = new LinkedBlockingQueue<Object>(2);
		
		try {
			for(int i=0;i<3;i++){
			//x.add("aaa");//报queue full 异常 返回true|false
			//x.put("bbb");//阻塞线程，停止放入对象,void
			System.out.println(x.offer("ccc"));//返回放入成功或者失败标志true|false
			System.out.println(x);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
