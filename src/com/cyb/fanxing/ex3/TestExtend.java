package com.cyb.fanxing.ex3;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月12日
 */
interface Iterable<T> {

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    Iterator<T> iterator();
}
interface MyListInterface<E> extends Iterable<E>{
	  //...
	  Iterator<E> iterator();
}

abstract class AbstractMyList<E> implements MyListInterface<E>{
	public Iterator<E> iterator(){return null;};//空实现
	public void add(E e){};
}

class MyList<E> extends AbstractMyList<E> {
	final Object[] items;
	int count =0;
	int max=0;
	public MyList(){
		items = new Object[10];//构造方法内可以对final进行初始化
		count=0;
		max=10;
	}
	public void add(E e){
		items[count++]=e;
	}
    //具体实现
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			@Override
			public boolean hasNext() {
				return count>0;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				return (E) items[count--];
			}

			@Override
			public void remove() {
				
			}
			
		};
	}
}

public class TestExtend {
	Log log = LogFactory.getLog(TestExtend.class);
	public static void main(String[] args) {
		AbstractMyList<Integer> my = new MyList<Integer>();
		for(int i=0;i<9;i++){
			my.add(i);
		}
		Iterator<Integer> iter = my.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}
