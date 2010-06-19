package com.way.sms;

import android.content.Context;

public class WayService {

	private SMSService smsService;

	public WayService(SMSService smsService) {
		this.smsService = smsService;
	}

	public boolean reply(WaySms waySms, Context context) {
		if (!waySms.isWayRequest())
			return false;

		return smsService.send(waySms.from(), waySms.generateReply());
	}
}