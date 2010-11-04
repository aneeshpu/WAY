package com.way.sms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.telephony.SmsMessage;
import android.util.Log;

public class FacebookTextMessage implements TextMessage {

	private static final Pattern WAY_MESSAGE_FORMAT = Pattern
			.compile("([a-zA-Z ]*) wrote on your Facebook Wall:\n([a-zA-Z ]*)\n*Reply to post on ([a-zA-Z]*)'s wall.\n\nReply \"sub\" to subscribe to ([a-zA-Z]*)'s status.");
	private final SmsMessage smsMessage;
	private final WayRequest wayRequest;
	private final Locator locator;

	public FacebookTextMessage(SmsMessage smsMessage, WayRequest wayRequest, Locator locator) {
		this.smsMessage = smsMessage;
		this.wayRequest = wayRequest;
		this.locator = locator;
	}

	@Override
	public boolean isWayRequest() {
		final Matcher matcher = matcher();
		Log.d("WAY", String.format("FacebookTextMessage:matcher.matches(): %s, smsMessage:%s", matcher.matches(), smsMessage.getMessageBody()));
		return matcher.matches() && wayRequest.isWayRequest(wallPost(matcher));
	}

	private String messageToPrint() {
		System.out.println("Actual method call");
		return "Actual method call";
	}

	private Matcher matcher() {
		final String messageBody = smsMessage.getMessageBody();
		final Matcher matcher = WAY_MESSAGE_FORMAT.matcher(messageBody);
		return matcher;
	}

	private String wallPost(final Matcher matcher) {
		return matcher.group(2);
	}

	@Override
	public String generateReply() {
		final Matcher matcher = matcher();
		matcher.matches();
		return String.format("Wall %s", lastKnownGeoLocation(locator).toString());
	}

	private GeoLocation lastKnownGeoLocation(Locator locator) {
		return locator.getCurrentGeoLocation();
	}

	private String sender(Matcher matcher) {
		return matcher.group(1);
	}

	@Override
	public String from() {
		return smsMessage.getOriginatingAddress();
	}

}
