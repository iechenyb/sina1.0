package com.cyb.collection.eunm;

public class UseColor {
	public static void main(String[] args) {
		System.out.println( isRed( Color.BLANK ) ) ;  //结果： false
        System.out.println( isRed( Color.RED ) ) ;    //结果： true
        showColor(Color.YELLOW);
        System.out.println("-------------------");
        //输出某一枚举的值
        System.out.println( ColorMap.RED.getName() );
        System.out.println( ColorMap.RED.getIndex() );
        //遍历所有的枚举
        for( ColorMap color : ColorMap.values()){
            System.out.println( color + "  name: " + color.getName() + "  index: " + color.getIndex()+ "  t: " + color.getT() );
        }
	}
	static boolean isRed( Color color ){
       return Color.RED.equals( color );
    }
	static void showColor(Color color){
        switch ( color ) {
        case BLANK:
            System.out.println( color );
            break;
        case RED :
            System.out.println( color );
            break;
        default:
            System.out.println( color );
            break;
        }
         
    }
}
