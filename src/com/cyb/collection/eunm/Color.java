package com.cyb.collection.eunm;

public enum Color {
	 RED, GREEN, BLANK, YELLOW ;
	
	public static boolean isRed(Color color){
		return RED.equals(color);
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
