package com.way.sms;

import android.telephony.SmsManager;
import android.util.Log;

public class SMSService {

	public boolean send(String to, String textMessage) {
		
		Log.d("way", String.format("sending sms %s tp %s", textMessage, to));
		if(textMessage == null) return false;
		SmsManager.getDefault().sendTextMessage(to, null, textMessage, null, null);
		return true;
	}

}
