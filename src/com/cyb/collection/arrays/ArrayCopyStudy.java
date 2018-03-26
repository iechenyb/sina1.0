package com.cyb.collection.arrays;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月26日
 */
public class ArrayCopyStudy {
	Log log = LogFactory.getLog(ArrayCopyStudy.class);
	public static void main(String[] args) {
		int[] ids = {1, 2, 3, 4, 5};  
		  
	    // 1、测试复制到别的数组上  
	    // 将ids数组的索引从0开始复制5个数，复制到ids2数组的索引从0开始  
	    int[] ids2 = new int[5];  
	    System.arraycopy(ids, 0, ids2, 0, 5);  
	    System.out.println(Arrays.toString(ids2)); // [1, 2, 3, 4, 5]  
	  
	    // 2、测试自我复制  
	    System.arraycopy(ids, 0, ids, 3, 2);  
	    System.out.println(Arrays.toString(ids)); // [1, 2, 3, 1, 2]  
	  
	  
	    // 3、如果是类型转换问题  
	    Object[] o1 = {1, 2, 3, 4.5, 6.7};  
	    Integer[] o2 = new Integer[5];  
	    try {  
	        System.arraycopy(o1, 0, o2, 0, o1.length);  
	    } catch (ArrayStoreException ex) {  
	        // 发生存储转换，部分成功的数据会被复制过去  
	        System.out.println("拷贝发生异常：数据转换错误，无法存储。");  
	    }  
	    // 从结果看，前面3个可以复制的数据已经被存储了。剩下的则没有  
	    System.out.println(Arrays.toString(o2)); // [1, 2, 3, null, null]
	}
}
