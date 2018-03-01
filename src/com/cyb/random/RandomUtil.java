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
	
	public static void main(String[] args) {
		System.out.println(randomInt(1,100));
		System.out.println(randomInt());
		Math.round(Math.random() * 10);//0-10之间的数
		new java.util.Random().nextInt(10);
		java.util.concurrent.ThreadLocalRandom.current().nextInt(10);
		Math.round(Math.random() * 10);
		Math.floor(Math.random() * 11);
	}
}
