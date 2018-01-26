package com.app.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.cyb.computer.ComputerUtil;

import sun.misc.BASE64Encoder;

public class PushUtil {
	public static Logger log = LoggerFactory.getLogger(PushUtil.class);
	public  static List<SocketIOClient> clients = new ArrayList<SocketIOClient>();
	public static SocketIOServer server;//保证单例模式
	public static boolean isStarted = false;
	public static void startPushServer() {
		try {
			Configuration config = new Configuration();
			config.setHostname(ComputerUtil.getRealIP());//ComputerUtil.getRealIP();172.17.162.26 192.168.0.151 g
			config.setPort(6677);
			System.out.println("http://"+ComputerUtil.getRealIP()+"/"+"6677");
			server = new SocketIOServer(config);
			PushListener listener = new PushListener(server);
			server.addEventListener("getmsg", Object.class, listener);
			server.addConnectListener(new ConnectListener() {
				public void onConnect(SocketIOClient client) {
					log.info("new sessionid=" + client.getSessionId());
					clients.add(client);
				}
			});
			server.addDisconnectListener(new DisconnectListener() {
				@Override
				public void onDisconnect(SocketIOClient client) {
					clients.remove(client);
				}
			});
			server.start();
			
			Timer timer = new Timer();  
	        timer.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                Random random = new Random();  
	                String data = "{\"x\":" +random.nextInt(100)+ ",\"y\":" +random.nextInt(100)+ "}";  
	                /*BASE64Encoder encoder = new BASE64Encoder();  
	                data = encoder.encode(data.getBytes());  */
	                for(SocketIOClient client : clients) {  
	                    client.sendEvent("pushpoint",data);  
	                }  
	            }  
	        }, 1000, 5000);
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
		/*Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            @Override  
            public void run() {  
                Random random = new Random();  
                String data = "{\"x\":" +random.nextInt(100)+ ",\"y\":" +random.nextInt(100)+ "}";  
                BASE64Encoder encoder = new BASE64Encoder();  
                data = encoder.encode(data.getBytes());  
                for(IOClient client : clients) {  
                    client.send(formatMessage(data));  
                }  
            }  
        }, 1000, 1000); */ 
		startPushServer();
	}
}
