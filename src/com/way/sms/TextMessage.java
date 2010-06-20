package com.way.sms;

public interface TextMessage {

	boolean isWayRequest();

	String generateReply();

	String from();

}
