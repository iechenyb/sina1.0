package com.cyb.shejimoshi.创建型;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月31日
 */
class StaticSendFactory {

	public static Sender produceMail() {
		return new MailSender();
	}

	public static Sender produceSms() {
		return new SmsSender();
	}
}

public class 静态工厂 {
	Log log = LogFactory.getLog(静态工厂.class);

	public static void main(String[] args) {
		Sender sender = StaticSendFactory.produceMail();
		sender.Send();
	}
}
