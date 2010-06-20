package com.way.sms;

import android.content.Context;

public class WaySMSFactory {

	public RegularTextMessage create(Object[] pdus, Context context) {
		return new RegularTextMessage(android.telephony.SmsMessage.createFromPdu((byte[]) pdus[0]), new Locator(new MyGeoCoder(), context), new WayRequest(), null);
	}

}
