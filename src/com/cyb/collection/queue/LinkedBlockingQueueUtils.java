package com.cyb.collection.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/*阻塞队列 实现是线程安全的，实现了先进先出等特性，是作为生产者消费者的首选
可以指定容量，也可以不指定，不指定的话，默认最大是Integer.MAX_VALUE，其中主要用到put和take方法，
put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。*/
public class LinkedBlockingQueueUtils {
public static void main(String[] args) throws InterruptedException {
	BlockingQueue<Object> x = new LinkedBlockingQueue<Object>();
	x.add("aaa");
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
	System.out.println(x);
	System.out.println("get:"+x.take());
}
}
