package com.way.sms;

public class GeoLocation {

	public static final GeoLocation Null = new GeoLocation(null,null);
	
	private final String address;
	private final String provider;

	public GeoLocation(String address, String provider) {
		this.address = address;
		this.provider = provider;
	}

	@Override
	public String toString() {
		return address + ", \n--provider:" + provider;
	}
}
