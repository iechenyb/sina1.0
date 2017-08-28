package com.cyb.collection.stack;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月28日
 */
public class StackUtils {
	Log log = LogFactory.getLog(StackUtils.class);
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(1);
		stack.add(2);
		stack.add(3);
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
		stack.pop();//为空时会报异常！
	}
}
