package com.cyb.shejimoshi.创建型;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年1月31日
 */
interface Provider {
	public Sender produce();
}

class SendMailFactory implements Provider {

	@Override
	public Sender produce() {
		return new MailSender();
	}
}

class SendSmsFactory implements Provider {

	@Override
	public Sender produce() {
		return new SmsSender();
	}
}

public class 抽象工厂 {
	Log log = LogFactory.getLog(抽象工厂.class);

	public static void main(String[] args) {
		Provider provider = new SendMailFactory();
		Sender sender = provider.produce();
		sender.Send();
	}
}
