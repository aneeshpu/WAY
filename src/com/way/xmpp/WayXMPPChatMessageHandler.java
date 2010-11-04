package com.way.xmpp;

import com.way.sms.handler.WayHandler;

public class WayXMPPChatMessageHandler implements WayHandler {

	private final XMPPChatMessage xmppChatMessage;
	private XMPPMessageSender xmppMessageSender;

	public WayXMPPChatMessageHandler(XMPPChatMessage xmppChatMessage, XMPPMessageSender xmppMessageSender) {
		this.xmppChatMessage = xmppChatMessage;
		this.xmppMessageSender = xmppMessageSender;
	}

	@Override
	public void handle() {
		if(xmppChatMessage.isWayRequest())
			xmppMessageSender.send(xmppChatMessage.from(), xmppChatMessage.reply());
	}


}
