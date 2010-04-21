package com.way.sms;

public class GeoLocation {

	public static final GeoLocation Null = new GeoLocation(null,null){

		@Override
		public String toString() {
			return "Doh! I couldn't figure out where I am. Could you check back a little later please";
		}
		
	};
	
	private final String address;
	private final String provider;

	public GeoLocation(String address, String provider) {
		this.address = address;
		this.provider = provider;
	}

	@Override
	public String toString() {
		return String.format("My last known location according to %s is, %s",provider, address);
	}
}
