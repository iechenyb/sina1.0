package com.cyb.collection.utils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月17日
 */
public class List效率比较 {
	Log log = LogFactory.getLog(List效率比较.class);
	private static final int COUNT = 100000;
    private static LinkedList linkedList = new LinkedList();
    private static ArrayList arrayList = new ArrayList();
    private static Vector vector = new Vector();
    /*同步安全Vector可以实现可增长的动态对象数组容量增加方式不同，
    Vector默认增长为原来一培，而ArrayList却是原来的一半+1*/
    private static Stack stack = new Stack();//同步安全 Stack是继承于Vector。
    public static void main(String[] args) {
        // 换行符
        System.out.println();
        // 插入
        insertByPosition(stack) ;
        insertByPosition(vector) ;
        insertByPosition(linkedList) ;
        insertByPosition(arrayList) ;
        // 换行符
        System.out.println();
        // 随机读取
        readByPosition(stack);
        readByPosition(vector);
        readByPosition(linkedList);
        readByPosition(arrayList);
        // 换行符
        System.out.println();
        // 删除 
        deleteByPosition(stack);
        deleteByPosition(vector);
        deleteByPosition(linkedList);
        deleteByPosition(arrayList);
    }
    // 获取list的名称
    private static String getListName(List list) {
        if (list instanceof LinkedList) {
            return "LinkedList";
        } else if (list instanceof ArrayList) {
            return "ArrayList";
        } else if (list instanceof Stack) {
            return "Stack";
        } else if (list instanceof Vector) {
            return "Vector";
        } else {
            return "List";
        }
    }
    // 向list的指定位置插入COUNT个元素，并统计时间
    private static void insertByPosition(List list) {
        long startTime = System.currentTimeMillis();
        // 向list的位置0插入COUNT个数
        for (int i=0; i<COUNT; i++)
            list.add(0, i);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(getListName(list) + " : insert "+COUNT+" elements into the 1st position use time：" + interval+" ms");
    }
    // 从list的指定位置删除COUNT个元素，并统计时间
    private static void deleteByPosition(List list) {
        long startTime = System.currentTimeMillis();
        // 删除list第一个位置元素
        for (int i=0; i<COUNT; i++)
            list.remove(0);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(getListName(list) + " : delete "+COUNT+" elements from the 1st position use time：" + interval+" ms");
    }
    // 根据position，不断从list中读取元素，并统计时间
    private static void readByPosition(List list) {
        long startTime = System.currentTimeMillis();
        // 读取list元素
        for (int i=0; i<COUNT; i++)
            list.get(i);
        long endTime = System.currentTimeMillis();
        long interval = endTime - startTime;
        System.out.println(getListName(list) + " : read "+COUNT+" elements by position use time：" + interval+" ms");
    }
    
    /**
     *  Stack : insert 100000 elements into the 1st position use time：1151 ms
		Vector : insert 100000 elements into the 1st position use time：1092 ms
		LinkedList : insert 100000 elements into the 1st position use time：7 ms
		ArrayList : insert 100000 elements into the 1st position use time：1085 ms
		
		Stack : read 100000 elements by position use time：5 ms
		Vector : read 100000 elements by position use time：4 ms
		LinkedList : read 100000 elements by position use time：4725 ms
		ArrayList : read 100000 elements by position use time：1 ms
		
		Stack : delete 100000 elements from the 1st position use time：1218 ms
		Vector : delete 100000 elements from the 1st position use time：1050 ms
		LinkedList : delete 100000 elements from the 1st position use time：5 ms
		ArrayList : delete 100000 elements from the 1st position use time：1202 ms
     */
}
