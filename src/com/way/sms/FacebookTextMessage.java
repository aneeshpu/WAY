package com.way.sms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.telephony.SmsMessage;

public class FacebookTextMessage implements TextMessage {

	private static final Pattern WAY_MESSAGE_FORMAT = Pattern
			.compile("([a-zA-Z ]*) Wrote on your Facebook Wall:\n([a-zA-Z ]*)\nReply to post on ([a-zA-Z]*)'s wall.\nReply \"sub\" to subscribe to ([a-zA-Z]*)'s status");
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
		return matcher.matches() && wayRequest.isWayRequest(wallPost(matcher));
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
		return String.format("Wall %s %s", sender(matcher), lastKnownGeoLocation(locator).toString());
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
