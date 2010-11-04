package com.way.xmpp;

import org.jivesoftware.smack.packet.Message;

import com.way.sms.TextMessage;
import com.way.sms.WayRequest;

public class XMPPChatMessage implements TextMessage {

	private final Message message;
	private final WayRequest wayRequest;

	public XMPPChatMessage(Message message, WayRequest wayRequest) {
		this.message = message;
		this.wayRequest = wayRequest;
	}

	@Override
	public boolean isWayRequest() {
		String chatText = message.getBody();
		return wayRequest.isWayRequest(chatText);
	}

	@Override
	public String generateReply() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String from() {
		// TODO Auto-generated method stub
		return null;
	}

}
