package com.cyb.socket.one2one;

public class Message {
	//��½ʱ��Ҫ�ṩ��������һ��id������֤
	public String userId;
	public String userName;
	public String fromSocket ;
	public String toSocket;
	public String content;
	public String sendTime;
	public String getFromSocket() {
		return fromSocket;
	}
	public void setFromSocket(String fromSocket) {
		this.fromSocket = fromSocket;
	}
	public String getToSocket() {
		return toSocket;
	}
	public void setToSocket(String toSocket) {
		this.toSocket = toSocket;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}
