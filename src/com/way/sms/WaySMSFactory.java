package com.way.sms;

import com.way.sms.handler.DefaultTextMessageHandler;
import com.way.sms.handler.TextMessageHandler;

import android.content.Context;

public class WaySMSFactory {

	public TextMessageHandler create(Object[] pdus, Context context) {
		final RegularTextMessage regularTextMessage = new RegularTextMessage(android.telephony.SmsMessage.createFromPdu((byte[]) pdus[0]), new Locator(new MyGeoCoder(), context), new WayRequest());
		return new TextMessageHandler(regularTextMessage, new SMSService(), new DefaultTextMessageHandler());
	}

}
