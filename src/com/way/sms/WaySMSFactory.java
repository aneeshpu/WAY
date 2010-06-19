package com.way.sms;

import android.content.Context;

public class WaySMSFactory {

	public WaySms create(Object[] pdus, Context context) {
		return new WaySms(android.telephony.SmsMessage.createFromPdu((byte[]) pdus[0]), new Locator(new MyGeoCoder(), context));
	}

}
