package com.way.xmpp;

import com.way.sms.Locator;
import com.way.sms.MyGeoCoder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class XMPPService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);

		String username = intent.getExtras().getString("username");
		String password = intent.getExtras().getString("password");

		Log.i("WAY", String.format("The username is %s", username));
		Log.i("WAY", String.format("The password is %s", password));

		XMPPServerConnector xmppServerConnector = new XMPPServerConnector("talk.google.com", 5222, "gmail.com", new Locator(new MyGeoCoder(), this));
		xmppServerConnector.login(username, password);

	}

}
