package com.app.push;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.cyb.computer.ComputerUtil;

public class PushUtil {
	public static Logger log = LoggerFactory.getLogger(PushUtil.class);
	public  static List<SocketIOClient> clients = new ArrayList<SocketIOClient>();
	public static SocketIOServer server;
	public static boolean isStarted = false;
	public static void startPushServer() {
		try {
			Configuration config = new Configuration();
			config.setHostname(ComputerUtil.getRealIP());//ComputerUtil.getRealIP();172.17.162.26 192.168.0.151 g
			config.setPort(6677);
			server = new SocketIOServer(config);
			PushListener listener = new PushListener(server);
			server.addEventListener("getmsg", Object.class, listener);
			server.addConnectListener(new ConnectListener() {
				public void onConnect(SocketIOClient client) {
					log.info("new sessionid=" + client.getSessionId());
					clients.add(client);
				}
			});
			server.start();
			log.info("puser server started!");
		    Object object = new Object();
		    synchronized (object) {
		      object.wait();
		    }
		} catch (Exception e) {
			log.info("push server start ocurr exception!\n"+e.toString());
		}
	}
	public static void main(String[] args) {
		
		startPushServer();
	}
}
