package com.way.xmpp;

import org.jivesoftware.smack.packet.Message;

import com.way.sms.Locator;
import com.way.sms.TextMessage;
import com.way.sms.WayRequest;

public class XMPPChatMessage implements TextMessage {

	private final Message message;
	private final WayRequest wayRequest;
	private final Locator locator;

	public XMPPChatMessage(Message message, WayRequest wayRequest, Locator locator) {
		this.message = message;
		this.wayRequest = wayRequest;
		this.locator = locator;
	}

	@Override
	public boolean isWayRequest() {
		String chatText = message.getBody();
		return wayRequest.isWayRequest(chatText);
	}

	@Override
	public String reply() {
		return locator.getCurrentGeoLocation().toString();
	}

	@Override
	public String from() {
		return message.getFrom();
	}

}
