package com.way.sms.handler;

import com.way.sms.SMSService;
import com.way.sms.TextMessage;

public class TextMessageHandler implements WayHandler {

	private final TextMessage textMessage;
	private final SMSService smsService;
	private final WayHandler wayHandler;

	public TextMessageHandler(TextMessage textMessage, SMSService smsService, WayHandler wayHandler) {
		this.textMessage = textMessage;
		this.smsService = smsService;
		this.wayHandler = wayHandler;
	}

	public void handle() {
		if(textMessage.isWayRequest())
			sendReply(textMessage.from(),textMessage.generateReply());
		
		wayHandler.handle();
	}

	private boolean sendReply(String to, String reply) {
		return smsService.send(to, reply);
	}

}
