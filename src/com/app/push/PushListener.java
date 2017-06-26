package com.app.push;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.iqiyi.MovieUtils;
import com.app.iqiyi.VedioDbUtils;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class PushListener implements DataListener<Object>{
  public static Logger log = LoggerFactory.getLogger(PushListener.class);
  SocketIOServer server;
  public PushListener(SocketIOServer server){
  	this.server = server;
  }
  public void onData(SocketIOClient client, Object action, AckRequest req)  {
	  try{
		System.out.println(" "+action+",client:"+client);
		new  VedioDbUtils("vedio");
		if("getVipMovies".equals(JSONObject.fromObject(action).get("cmd"))){
			client.sendEvent("getVipMovies",VedioDbUtils.getVedio("movie"));
		}else if("getVipVideos".equals(JSONObject.fromObject(action).get("cmd"))){
			client.sendEvent("getVipVideos",VedioDbUtils.getVedio("vedio"));
		}else if("getVipVideosInfo".equals(JSONObject.fromObject(action).get("cmd"))){
			client.sendEvent("getVipVideosInfo",MovieUtils.getVedios(JSONObject.fromObject(action).get("url").toString()));
		}
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}