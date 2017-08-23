package com.cyb.collection.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
/*** 
 * 这是一个基于优选级队列，它具有可阻塞的读取操作。 
 * 下面是一个示例，其中在优级级队列的对象是按照优级级顺序从队列中出现的任务。 
 * prioritizedTask被赋予了一个优先级数字， 以此来提供这种顺序。 
 * 
 */  
public class PriorityBlockingQueueUtils {
public static void main(String[] args) throws InterruptedException {
	BlockingQueue<Object> x = new PriorityBlockingQueue<Object>();
	x.add("aaa");
	x.put("bbb");
	x.offer("ccc");
	System.out.println(x);
	x.take();
	System.out.println(x);
	x.peek();
	System.out.println(x);
	x.poll();
	System.out.println(x);
	x.remove("ccc");
	System.out.println(x);
	x.take();//注意：没有元素可用的时候回阻塞进程。导致最有一个语句无法执行一直等待。
	System.out.println("get:"+x);
}
}
