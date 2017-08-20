package com.cyb.collection.queue;

import java.util.Deque;
import java.util.LinkedList;

//双向列表
public class DequeUtils {
	public static void main(String[] args) {
		
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        deque.offer("d");
        deque.add("e");
        System.out.println(deque);
        //获取栈首元素后，元素不会出栈
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        while(deque.size() > 0) {
            //获取栈首元素后，元素将会出栈
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }
}
