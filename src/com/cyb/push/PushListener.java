package com.cyb.push;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
/**
 * 推送监听器
 * @author DHUser
 *
 */
public class PushListener implements DataListener<Object>{
  public static Logger log = LoggerFactory.getLogger(PushListener.class);
  SocketIOServer server;
  public PushListener(SocketIOServer server){
  	this.server = server;
  }
  public void onData(SocketIOClient client, Object param, AckRequest req)  {
	  try{
		/**
		 * 事件发起方式：socket.emit('getmsg', "pushpoint")；
		 * 此时的 param = pushTotalFundsAndDate
		 */
		 Random random = new Random();
		log.debug(" "+param+",client:"+client);
		Point point  = new Point(random.nextInt(100), random.nextInt(100));
		client.sendEvent("pushpoint", point);//向主动拉取数据的客户端推送预期的数据包
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}