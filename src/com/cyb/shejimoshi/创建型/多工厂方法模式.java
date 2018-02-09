package com.cyb.shejimoshi.创建型;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月31日
 */
class MutilSendFactory {
	public Sender produceMail() {
		return new MailSender();
	}

	public Sender produceSms() {
		return new SmsSender();
	}
}

public class 多工厂方法模式 {
	Log log = LogFactory.getLog(多工厂方法模式.class);

	public static void main(String[] args) {
		MutilSendFactory factory = new MutilSendFactory();
		Sender sender = factory.produceMail();
		sender.Send();
		sender = factory.produceSms();
		sender.Send();
	}
}
