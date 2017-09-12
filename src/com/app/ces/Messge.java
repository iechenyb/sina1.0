package com.app.ces;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月1日
 */
public class Messge {
	Log log = LogFactory.getLog(Messge.class);
	private String msgType;//消息类型  如dhdata 东航数据  hkgg 香港港股 hkwh 香港外汇 mjdata 盟军数据
	private String order ;//通知数据导入指令 KIIIK01 发送短信指令 KIIIK02
	private String msgContent;//消息内容
	private Long transferTime;//传输时间
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getMsgContent() {
		return msgContent;
	}
	
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	
	public Long getTransferTime() {
		return transferTime;
	}
	
	public void setTransferTime(Long transferTime) {
		this.transferTime = transferTime;
	}
}
