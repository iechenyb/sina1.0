package com.cyb;

import com.cyb.computer.ComputerUtil;

public class Contanst {
	public static int port = 6677;
	public static String pushUrl  ;
	public String name ="chenyb";
	static{
		pushUrl = "http://"+ComputerUtil.getRealIP()+":"+port;
		System.out.println(pushUrl);
	}
}
