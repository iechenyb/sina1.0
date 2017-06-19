package com.cyb.youhua;
/**
 * 典型的递归调用算法
 * 0和n(n>=1)位置依次交换，n-i个数做递归，直到n-i==0结束。
 * @author DHUser
 * 第一次交换时   j=i 
 */
public class 排列 {
	 public static void permutation(char[]ss,int i){  
	        if(ss==null||i<0 ||i>ss.length){  
	            return;  
	        }  
	        if(i==ss.length){  
	            System.out.println(new String(ss));  
	        }else{  
	            for(int j=i;j<ss.length;j++){  
	                char temp=ss[j];//交换前缀,使之产生下一个前缀  
	                ss[j]=ss[i];  
	                ss[i]=temp;  
	                permutation(ss,i+1);  
	                temp=ss[j]; //将前缀换回来,继续做上一个的前缀排列，回撤交换值 
	                ss[j]=ss[i];  
	                ss[i]=temp; 
	            }  
	        }  
	    }  
	    public static void main(String args[]){  
	        char []ss={'a','b','c'};  //,'d'
	        permutation(ss,0); 
	        swap(2,3);
	    }  
	    public static  void swap(int a,int b){
	    	int tmp = a;
	    	a=b;
	    	b=tmp;
	    	System.out.println("a="+a+",b="+b);
	    	tmp = a;
	    	a=b;
	    	b=tmp;
	    	System.out.println("a="+a+",b="+b);//第二次测撤销第一次的交换 偶数倍交换是撤销交换
	    	tmp = a;
	    	a=b;
	    	b=tmp;
	    	System.out.println("a="+a+",b="+b);
	    }
}
