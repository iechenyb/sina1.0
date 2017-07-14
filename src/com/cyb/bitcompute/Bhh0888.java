package com.cyb.bitcompute;
/**
 * �˻ʺ����� 8*8����
 * @author DHUser
 *
 */
public class Bhh0888 {
   static int bin_col=007;
   static int bin_row=070;
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
