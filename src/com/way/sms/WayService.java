package com.way.sms;

import android.content.Context;

public class WayService {

	private GeoLocationService geoLocationService;
	private SMSService smsService;

	public WayService(SMSService smsService, GeoLocationService geoLocationService) {
		this.smsService = smsService;
		this.geoLocationService = geoLocationService;
	}

	public boolean reply(WaySms waySms, Context context) {
		if (!waySms.isWayRequest())
			return false;

		return smsService.send(waySms.from(), geoLocationService.getCurrentGeoLocation(context).toString());
	}
}