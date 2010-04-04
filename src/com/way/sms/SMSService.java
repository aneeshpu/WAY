package com.way.sms;

import android.telephony.SmsManager;

public class SMSService {

	public boolean send(String to, String textMessage) {
		SmsManager.getDefault().sendTextMessage(to, null, textMessage, null, null);
		return true;
	}

}
