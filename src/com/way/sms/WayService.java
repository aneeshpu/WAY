package com.way.sms;


public class WayService {

	private SMSService smsService;

	public WayService(SMSService smsService) {
		this.smsService = smsService;
	}

	public boolean reply(RegularTextMessage waySms) {
		if (!waySms.isWayRequest())
			return false;

		return smsService.send(waySms.from(), waySms.reply());
	}
}