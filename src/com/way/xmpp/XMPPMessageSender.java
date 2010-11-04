package com.way.xmpp;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import android.util.Log;

public class XMPPMessageSender {

	private final XMPPConnection connection;

	public XMPPMessageSender(XMPPConnection connection) {
		this.connection = connection;

	}

	public void send(String to, String message) {
		Chat chat = connection.getChatManager().createChat(to, new MessageListener() {

			@Override
			public void processMessage(Chat arg0, Message arg1) {

			}
		});

		try {
			chat.sendMessage(message);
			Log.d("WAY", String.format("Sent message %s to %s", message, to));
		} catch (XMPPException e) {
			Log.e("WAY", String.format("failed to send reply to %s", to), e);
		}
	}
}
