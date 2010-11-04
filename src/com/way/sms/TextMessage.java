package com.way.sms;

public interface TextMessage {

	boolean isWayRequest();

	String reply();

	String from();

}
