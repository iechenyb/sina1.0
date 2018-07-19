package com.app.csdn;

import com.cyb.cmd.CmdUtils;

public class HelloWorld {
	public static void main(String[] args) {
		boolean rs = CmdUtils.exeCMD("telnet 118.244.236.27 10800");
		System.out.println(rs);
	}
}
