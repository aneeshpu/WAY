package com.way.sms;

import com.way.sms.handler.DefaultTextMessageHandler;
import com.way.sms.handler.TextMessageHandler;

import android.content.Context;
import android.telephony.SmsMessage;
import android.util.Log;

public class WaySMSFactory {

	public TextMessageHandler create(Object[] pdus, Context context) {
		final SmsMessage smsMessage = android.telephony.SmsMessage.createFromPdu((byte[]) pdus[0]);
		Log.d("WAY", smsMessage.getMessageBody());
		final Locator locator = new Locator(new MyGeoCoder(), context);
		final WayRequest wayRequest = new WayRequest();
		final SMSService smsService = new SMSService();
		
		final RegularTextMessage regularTextMessage = new RegularTextMessage(smsMessage, locator, wayRequest);
		final FacebookTextMessage facebookTextMessage = new FacebookTextMessage(smsMessage, wayRequest, locator);
		
		final TextMessageHandler facebookMessageHandler = new TextMessageHandler(facebookTextMessage, smsService, new DefaultTextMessageHandler());
		return new TextMessageHandler(regularTextMessage, smsService, facebookMessageHandler);
	}

}
