package com.cyb.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ArrayBlockingQueueUtils {
public static void main(String[] args) {
	BlockingQueue<Object> x = new ArrayBlockingQueue<Object>(100);
	System.out.println(x);
	
}
}
