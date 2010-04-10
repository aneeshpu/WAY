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

	public GeoLocation getCurrentGeoLocation(Context context) {

		final LocationManager locationService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		final List<String> allProviders = locationService.getAllProviders();
//		System.setProperty("log.tag.WAY", "DEBUG");
		
		return askGPS(context, locationService);
//		return askAllProviders(context, locationService, allProviders);
		
	}

	private GeoLocation askGPS(Context context, LocationManager locationService) {
		return askProvider(context, locationService, "gps");
	}

	private GeoLocation askAllProviders(Context context, final LocationManager locationService, final List<String> allProviders) {
		for (String provider : allProviders) {

//			Log.d("way", String.format("---provider:%s",provider));
			
			final GeoLocation lastKnownGeoLocation = askProvider(context, locationService, provider);
			if(lastKnownGeoLocation != null)return lastKnownGeoLocation;
		}
		
		return GeoLocation.Null;
	}

	private GeoLocation askProvider(Context context, final LocationManager locationService, String provider) {
		final Location lastKnownLocation = locationService.getLastKnownLocation(provider);
		final List<Address> fromLocation = myGeoCoder.getFromLocation(context, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1);

		final String address = makeAddress(fromLocation);
//			Log.d("way", address);
		if (address != null)
			return new GeoLocation(address, provider);
		
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
		return String.format("%s,%s", address.getAddressLine(0), address.getAddressLine(1));
	}
}