package com.cyb.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueUtils {
public static void main(String[] args) throws InterruptedException {
	BlockingQueue<Object> x = new ArrayBlockingQueue<Object>(100);
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
	x.take();
	System.out.println(x);
}
}
