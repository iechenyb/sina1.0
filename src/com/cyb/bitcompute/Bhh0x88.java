package com.cyb.bitcompute;
/**
 * �˻ʺ����� 8*8����
 * @author DHUser
 *
 */
public class Bhh0x88 {
   static int bin_col=0x07;//0x����
   static int bin_row=0x70;
   public static boolean isSameCol(int a,int b){
	   a = a&bin_col;
	   b = b&bin_col;
	   if(a==b){//�Ƚϵڶ�λ
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
	 System.out.println("b��dͬ�У�"+isSameRow(bpoint, dpoint));
	 System.out.println("e��dͬ�У�"+isSameCol(epoint, dpoint));
	 System.out.println("b��dͬ�У�"+isSameCol(bpoint, dpoint));
	 System.out.println("e��dͬ�У�"+isSameRow(epoint, dpoint));
	 System.out.println("a��cͬ�У�"+isSameCol(apoint, cpoint));
	 System.out.println("a��cͬ�У�"+isSameRow(apoint, cpoint));
	 System.out.println("b��c�Խ��ߣ�"+isDiagonal(bpoint, cpoint));
	 System.out.println("f��g�Խ��ߣ�"+isDiagonal(fpoint, gpoint));
	 /**
	  * b��dͬ�У�true
		e��dͬ�У�true
		b��dͬ�У�false
		e��dͬ�У�false
		a��cͬ�У�false
		a��cͬ�У�false
		b��c�Խ��ߣ�true
		f��g�Խ��ߣ�true
	  */
   }
}
