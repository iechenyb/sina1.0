package com.cyb.bitcompute;
/**
 * 八皇后问题 8*8矩阵
 * @author DHUser
 *
 */
public class Bhh0x88 {
   static int bin_col=0x07;//0x行列
   static int bin_row=0x70;
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
	   int sub1 = Math.abs( (a>>4) - (b>>4) );
	   int sub2 = Math.abs((a&bin_col)-(b&bin_col));
	   if(sub1 == sub2){
		   return true;
	   }else{
		   return false;
	   }
   }
   public static void main(String[] args) {
	 int apoint=0x40;
	 int bpoint=0x11;
	 int cpoint=0x22;
	 int dpoint=0x14;
	 int epoint=0x54;
	 int fpoint=0x06;
	 int gpoint=0x17;
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
