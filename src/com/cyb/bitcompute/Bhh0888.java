package com.cyb.bitcompute;
/**
 * 八皇后问题 8*8矩阵
 * @author DHUser
 *
 */
public class Bhh0888 {
   static int bin_col=007;
   static int bin_row=070;
   public static boolean isSameCol(int a,int b){
	   a = a&bin_col;
	   b = b&bin_col;
	   if(a==b){//比较第二位
		   return true;
	   }else{
		   return false;
	   }
   }
   public static  boolean isSameRow(int a,int b){
	   a = a&bin_row;
	   b = b&bin_row;
	   if(a==b){
		   return true;
	   }else{
		   return false;
	   }
   }
   public static boolean isDiagonal(int a,int b){
	   int sub1 = Math.abs( (a>>3) - (b>>3) );
	   int sub2 = Math.abs((a&bin_col)-(b&bin_col));
	   if(sub1 == sub2){
		   return true;
	   }else{
		   return false;
	   }
   }
   public static void main(String[] args) {
	 int apoint=040;
	 int bpoint=011;
	 int cpoint=022;
	 int dpoint=014;
	 int epoint=054;
	 int fpoint=006;
	 int gpoint=017;
	 System.out.println("b和d同行？"+isSameRow(bpoint, dpoint));
	 System.out.println("e和d同列？"+isSameCol(epoint, dpoint));
	 System.out.println("b和d同列？"+isSameCol(bpoint, dpoint));
	 System.out.println("e和d同行？"+isSameRow(epoint, dpoint));
	 System.out.println("a和c同列？"+isSameCol(apoint, cpoint));
	 System.out.println("a和c同行？"+isSameRow(apoint, cpoint));
	 System.out.println("b和c对角线？"+isDiagonal(bpoint, cpoint));
	 System.out.println("f和g对角线？"+isDiagonal(fpoint, gpoint));
	 /**
	  * b和d同行？true
		e和d同列？true
		b和d同列？false
		e和d同行？false
		a和c同列？false
		a和c同行？false
		b和c对角线？true
		f和g对角线？true
	  */
   }
}
