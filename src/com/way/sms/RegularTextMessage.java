package com.way.sms;


import android.telephony.SmsMessage;

class RegularTextMessage implements TextMessage{
	
	private final SmsMessage smsMessage;
	private final Locator locator;
	private WayRequest wayRequest;
	private final TextMessage textMessageHandler;

	public RegularTextMessage(android.telephony.SmsMessage smsMessage, Locator locator, WayRequest wayRequest, TextMessage textMessageHandler) {
		this.smsMessage = smsMessage;
		this.locator = locator;
		this.wayRequest = wayRequest;
		this.textMessageHandler = textMessageHandler;
	}

	public boolean isWayRequest() {
		final String messageBody = smsMessage.getMessageBody();
		return wayRequest.isWayRequest(messageBody) || delegateToNextHandler();
	}

	private boolean delegateToNextHandler() {
		return textMessageHandler != null && textMessageHandler.isWayRequest();
	}

	@Override
	public String from() {
		return smsMessage.getOriginatingAddress();
	}

	public String generateReply() {
		final GeoLocation currentGeoLocation = locator.getCurrentGeoLocation();
		return currentGeoLocation.toString();
	}
}
