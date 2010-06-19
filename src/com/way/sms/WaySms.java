package com.way.sms;

import java.util.regex.Pattern;

import android.telephony.SmsMessage;

class WaySms {

	private final SmsMessage smsMessage;
	private static final Pattern WAY_MESSAGE_FORMAT = Pattern.compile("[wW]here *[aA]?r?e? *[yY]?o?u? *[\\p{Punct}]*|WAY");
	private final Locator locator;

	public WaySms(android.telephony.SmsMessage smsMessage, Locator locator) {
		this.smsMessage = smsMessage;
		this.locator = locator;
	}

	public boolean isWayRequest() {
		final String messageBody = smsMessage.getMessageBody();
		return WAY_MESSAGE_FORMAT.matcher(messageBody).matches();
	}

	public String from() {
		return smsMessage.getOriginatingAddress();
	}

	public String generateReply() {
		final GeoLocation currentGeoLocation = locator.getCurrentGeoLocation();
		return currentGeoLocation.toString();
	}
}
