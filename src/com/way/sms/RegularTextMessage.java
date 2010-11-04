package com.way.sms;

import android.telephony.SmsMessage;

class RegularTextMessage implements TextMessage {

	private final SmsMessage smsMessage;
	private final Locator locator;
	private WayRequest wayRequest;

	public RegularTextMessage(android.telephony.SmsMessage smsMessage, Locator locator, WayRequest wayRequest) {
		this.smsMessage = smsMessage;
		this.locator = locator;
		this.wayRequest = wayRequest;
	}

	public boolean isWayRequest() {
		final String messageBody = smsMessage.getMessageBody();
		return wayRequest.isWayRequest(messageBody);
	}

	@Override
	public String from() {
		return smsMessage.getOriginatingAddress();
	}

	public String reply() {
		final GeoLocation currentGeoLocation = locator.getCurrentGeoLocation();
		return currentGeoLocation.toString();
	}
}
