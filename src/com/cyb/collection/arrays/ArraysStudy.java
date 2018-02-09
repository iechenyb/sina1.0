package com.cyb.collection.arrays;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * http://blog.csdn.net/liu_yanzhao/article/details/70847050
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月9日
 */
public class ArraysStudy {
	Log log = LogFactory.getLog(ArraysStudy.class);
	public static void main(String[] args) {
		int []arr = {3,2,1,5,4};
		Arrays.sort(arr,0,arr.length);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",");
		}
		System.out.println();
		String []strs = {"abc","adc","abd","ccc","bbcd"};
		Arrays.sort(strs,0,strs.length);
		for(int i=0;i<strs.length;i++){
			System.out.print(strs[i]+",");
		}
		System.out.println();
		int []arr1 = {1,2,3};
		int []arr2 = {1,2,3};
		int []arr3 = {1,2,4};
		System.out.println(Arrays.equals(arr1,arr2));
		System.out.println(Arrays.equals(arr1,arr3));
		
		
		int []arr11 = {10,20,30,40,50};
		System.out.println(Arrays.binarySearch(arr11, 20));
		System.out.println(Arrays.binarySearch(arr11, 25));
	}
}
