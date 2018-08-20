package com.cyb.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月14日
 */
public class ReadAndWriteLockTest {
	static Log log = LogFactory.getLog(ReadAndWriteLockTest.class);
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException {
		boolean expect=false;
		boolean update = true;
		int e = expect ? 1 : 0;//0 ex1 == exp2 ?1:0
        int u = update ? 1 : 0;//1
        System.out.println(e+","+u);
		// 获取对象属性在内存中相对对象的偏移地址
		rwl.readLock().lock();
		rwl.readLock().unlock();
		rwl.writeLock().lock();
		rwl.writeLock().unlock();
	}
}
