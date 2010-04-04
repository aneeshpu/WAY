package com.way.sms;

public class WaySMSFactory {

	public WaySms create(Object[] pdus) {
		return new WaySms(android.telephony.SmsMessage.createFromPdu((byte[]) pdus[0]));
	}

}
