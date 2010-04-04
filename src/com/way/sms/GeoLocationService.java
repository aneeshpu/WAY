package com.way.sms;

import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;

public class GeoLocationService {

	private MyGeoCoder myGeoCoder;
	
	public GeoLocationService(MyGeoCoder myGeoCoder){
		this.myGeoCoder = myGeoCoder;
		
	}

	public String getCurrentGeoLocation(Context context) {

		final LocationManager locationService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		final List<String> allProviders = locationService.getAllProviders();

		for (String provider : allProviders) {

			final Location lastKnownLocation = locationService.getLastKnownLocation(provider);
			final List<Address> fromLocation = myGeoCoder.getFromLocation(context, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1);

			final String address = makeAddress(fromLocation);
			if (address != null)
				return address;
		}
		
		return null;
	}

	private String makeAddress(List<Address> fromLocation) {
		if (fromLocation == null || fromLocation.isEmpty())
			return null;

		final StringBuilder stringBuilder = new StringBuilder();
		for (Address address : fromLocation) {
			stringBuilder.append(makeAddress(address));
		}

		return stringBuilder.toString();
	}

	private String makeAddress(Address address) {
		return address.toString();
	}
}