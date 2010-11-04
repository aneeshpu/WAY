package com.way.xmpp;

import org.jivesoftware.smack.packet.Message;

import com.way.sms.handler.WayHandler;

public class WayChatMessageHandler implements WayHandler {

	private final XMPPChatMessage xmppChatMessage;

	public WayChatMessageHandler(XMPPChatMessage xmppChatMessage) {
		this.xmppChatMessage = xmppChatMessage;
	}

	@Override
	public void handle() {
		if(xmppChatMessage.isWayRequest())
			sendReply(xmppChatMessage.from(),xmppChatMessage.reply());
	}

	private void sendReply(String from, String reply) {
		
	}


}
