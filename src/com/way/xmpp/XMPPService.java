package com.way.xmpp;

import com.way.sms.Locator;
import com.way.sms.MyGeoCoder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class XMPPService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		XMPPServerConnector xmppServerConnector = new XMPPServerConnector("talk.google.com", 5222, "gmail.com", new Locator(new MyGeoCoder(), this));
		xmppServerConnector.login("aneeshpu@gmail.com", "gatorade");
	}

	

}
