package com.cyb.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年7月27日
 */
public class CmdUtils {
	static Log log = LogFactory.getLog(CmdUtils.class);

	public static boolean exeCMD(String command) {
		boolean result = true;
		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec("cmd.exe " + command);
			if (process.getErrorStream().read() != -1) {
				result = false;
			}
			process.destroy();
			System.out.println("oer");
		} catch (Exception e) {
			result = false;
			log.info("Exe command error!");
		}
		return result;
	}

	public static String exeCMDWithResult(String command) {
		StringBuilder sb = new StringBuilder();
		try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c "+command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
			String line = null;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				sb.append(line+",");
			}
			br.close();
			p.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getLocalIPForCMD() {
		StringBuilder sb = new StringBuilder();
		String command = "cmd.exe /c ipconfig | findstr IPv4";
		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.substring(line.lastIndexOf(":") + 2, line.length());
				sb.append(line+",");
			}
			br.close();
			p.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getLocalIPForJava() {
		StringBuilder sb = new StringBuilder();
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						sb.append(inetAddress.getHostAddress().toString() + "\n");
					}
				}
			}
		} catch (SocketException e) {
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		//java -version 不可以查看
		/*System.out.println(exeCMDWithResult("netstat -a"));
		System.out.println(exeCMDWithResult("ipconfig | findstr IPv4"));*/
		System.out.println(getLocalIPForCMD());
		System.out.println(getLocalIPForJava());
		
		System.out.println(exeCMDWithResult("ping www.baidu.com").contains("找不到主机"));
	}
}
