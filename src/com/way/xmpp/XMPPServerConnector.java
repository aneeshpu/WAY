package com.way.xmpp;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;

import com.way.sms.WayRequest;

import android.util.Log;

public class XMPPServerConnector {

	private final String server;
	private final int port;
	private final String domain;

	public XMPPServerConnector(String server, int port, String domain) {
		this.server = server;
		this.port = port;
		this.domain = domain;
	}

	public boolean login(String username, String password) {
		ConnectionConfiguration connectionConfiguration = new ConnectionConfiguration(server, port, domain);
		XMPPConnection xmppConnection = new XMPPConnection(connectionConfiguration);

		try {
			xmppConnection.connect();
			Log.d("WAY", String.format("Connected to %s", server));

			xmppConnection.login(username, password);
			Log.d("WAY", String.format("Logged in as %s", username));

			PacketFilter messageFilter = new PacketTypeFilter(Message.class);

			xmppConnection.addPacketListener(new PacketListener() {

				@Override
				public void processPacket(Packet packet) {
					Message message = (Message) packet;

					new WayChatMessageHandler(new XMPPChatMessage(message, new WayRequest())).handle();
				}
			}, messageFilter);
			Log.d("WAY", "Registered a packet listener");
		} catch (XMPPException e) {
			Log.e("WAY", String.format("Failed to connect to %s", server, e));
		}

		return false;
	}
}
