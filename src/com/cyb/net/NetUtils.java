package com.cyb.net;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月27日
 */
public class NetUtils {
	Log log = LogFactory.getLog(NetUtils.class);
	public static InetAddress getIpv4Address() throws IOException {
		return getIpv4Address(null);
	}
	public static InetAddress getIpv4Address(String prefix) throws IOException {
		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress addr = null;
		boolean found = false;
		while (netInterfaces.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
			Enumeration<InetAddress> addrs = ni.getInetAddresses();
			while (addrs.hasMoreElements()) {
				addr = addrs.nextElement();
				if (!addr.isLoopbackAddress() && addr.getHostAddress().indexOf(":") == -1) {
					if (prefix == null) {
						found = true;
						break;
					}
					if (prefix != null && addr.getHostAddress().startsWith(prefix)) {	
						found = true;
						break;
					}
				} else {
					addr = null;
				}
			}
			
			if (found) { break; }
		}

		if (addr == null) {
			addr = InetAddress.getLocalHost();
		}

		return addr;
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 检查本地端口号是否使用<br>
	 *创建时间: 2017年7月15日hj12
	 *@param port
	 *@return
	 */
	public static boolean isLocalPortUsing(int port) {
		return isPortUsing("127.0.0.1", port)||isPortUsing("localhost", port);
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 检查远端端口号服务是否开启<br>
	 *创建时间: 2017年7月15日hj12
	 *@param host
	 *@param port
	 *@return
	 */
	@SuppressWarnings("resource")
	public static boolean isPortUsing(String host, int port) {
		try {
			new Socket(host, port);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	private NetUtils() {
		super();
	} 	
	
	public static void main(String[] args) throws IOException {
		System.out.println(isPortUsing("192.168.108.221",8088));
		System.out.println(isLocalPortUsing(8080));
		System.out.println(getIpv4Address());
	}
}
