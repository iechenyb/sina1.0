package com.cyb.diffcult.引用测试;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.mybatis.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月22日
 */
public class 强弱软虚引用 {
	Log log = LogFactory.getLog(强弱软虚引用.class);
	@SuppressWarnings({ "unused", "rawtypes" })
	public static void main(String[] args) {
		Object object = new Object();//强引用
		String str = "hello";//强引用
		/*软引用可以和一个引用队列（ReferenceQueue）联合使用，
		 * 如果软引用所引用的对象被JVM回收，这个软引用就会被加入到与之关联的引用队列中。
		 */
		SoftReference<String> sr = new SoftReference<String>(new String("hello"));
        System.out.println(sr.get());
        System.gc();
        System.out.println(sr.get());
        System.out.println("---------------------");
        User user = new User();
        user.setPassword("1111");
        user.setUsername("2222");
        WeakReference<User> wr = new WeakReference<User>(user);//new User()
        System.out.println(wr.get());
        //user = null; //手动释放，每次gc都为空，否则不一定
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println("先释放，后回收："+wr.get());//存在强引用user 不会被回收置空
        System.out.println("---------------------");
        WeakReference<String> wr1 = new WeakReference<String>(new String("aaaa"));
        System.out.println(wr1.get());
        System.gc();                //通知JVM的gc进行垃圾回收
        System.out.println(wr1.get());//无强引用相关联 被回收置空 
        System.out.println("---------------------");
        ReferenceQueue<String> queue = new ReferenceQueue<String>();
        //虚引用 虚引用必须和引用队列关联使用
        PhantomReference<String> pr = new PhantomReference<String>(new String("hello"), queue);
        System.out.println(pr.get());//null
        
        ReferenceQueue<User> queue1 = new ReferenceQueue<User>();
        PhantomReference<User> pr1 = new PhantomReference<User>(user, queue1);
        System.out.println(pr1.get());//null
        
        WeakHashMap map = new WeakHashMap();
	}
	/**https://www.cnblogs.com/dolphin0520/p/3784171.html
	 * 　对于强引用，我们平时在编写代码时经常会用到。而对于其他三种类型的引用，
	 * 使用得最多的就是软引用和弱引用，这2种既有相似之处又有区别。它们都是用来描述非必需对象的，
	 * 但是被软引用关联的对象只有在内存不足时才会被回收，
	 * 而被弱引用关联的对象在JVM进行垃圾回收时总会被回收。
	 */
}
