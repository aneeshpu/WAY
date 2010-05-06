package com.way.sms;

import java.util.regex.Pattern;

import android.telephony.SmsMessage;

class WaySms {

	private final SmsMessage smsMessage;

	public WaySms(android.telephony.SmsMessage smsMessage) {
		this.smsMessage = smsMessage;
	}

	public boolean isWayRequest() {
		final String messageBody = smsMessage.getMessageBody();
		final Pattern pattern = Pattern.compile("[wW]here *[aA]?r?e? *[yY]?o?u? *[\\p{Punct}]*|WAY");
		return pattern.matcher(messageBody).matches();
	}

	public String from() {
		return smsMessage.getOriginatingAddress();
	}

}
