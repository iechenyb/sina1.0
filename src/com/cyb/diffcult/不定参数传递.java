package com.cyb.diffcult;

public class 不定参数传递 {
	public static void main(String[] args){  
        testPoints("I love my job.");//一个参数传入  
        testPoints("you","and","me");//3个String参数传入  
        testPoints(new String[]{"you","and","me"});//可以看到传入三个String参数和传入一个长度为3的数组结果一样，再看例子  
        System.out.println("---------------------------------------------------------");  
          
        testPoints(7);  
        testPoints(7,9,11);  
        testPoints(new Integer[]{7,9,11});  
    }  
      
    public static void testPoints(String... s){  
        if(s.length==0){  
            System.out.println("没有参数传入！");  
        }else if(s.length==1){  
            System.out.println("1个参数传入！it is :"+s[0]);  
        }else{      
            System.out.println("the input string is-->");  
            for(int i=0;i<s.length;i++){  
                System.out.println("第"+(i+1)+"个参数是"+s[i]+";");  
            }      
            System.out.println();  
        }  
    }  
      
    public static void testPoints(Integer... itgr){  
        if(itgr.length==0){  
            System.out.println("没有Integer参数传入！");  
        }else if(itgr.length==1){  
            System.out.println("1个Integer参数传入！it is :"+itgr[0]);  
        }else{      
            System.out.println("the input string is-->");  
            for(int i=0;i<itgr.length;i++){  
                System.out.println("第"+(i+1)+"个Integer参数是"+itgr[i]+";");  
            }      
            System.out.println();  
        }  
    }  
}
