package com.cyb.random;

import java.util.Random;

public class RandomUtil {
	private static Random random = new Random();
	public static int randomInt(int min,int max){
		if(max<=min){ return 1;}
		return random.nextInt(max) % (max - min + 1) + min;
	}
	public static int randomInt(){
		return random.nextInt();
	}
}
