package com.way.sms;

import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;

public class Locator {

	private MyGeoCoder myGeoCoder;
	private final Context context;

	public Locator(MyGeoCoder myGeoCoder, Context context) {
		this.myGeoCoder = myGeoCoder;
		this.context = context;

	}

	public GeoLocation getCurrentGeoLocation() {
		final LocationManager locationService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		return askAllProviders(context, locationService, locationService.getAllProviders());
	}

	private GeoLocation askAllProviders(Context context, final LocationManager locationService, final List<String> allProviders) {
		for (String provider : allProviders) {

			final GeoLocation lastKnownGeoLocation = askProvider(context, locationService, provider);
			if (lastKnownGeoLocation != null)
				return lastKnownGeoLocation;
		}

		return GeoLocation.Null;
	}

	private GeoLocation askProvider(Context context, final LocationManager locationService, String provider) {
//		Log.d("way", String.format("---provider:%s", provider));

		final Location lastKnownLocation = locationService.getLastKnownLocation(provider);
		if (lastKnownLocation == null)
			return null;
		final List<Address> fromLocation = myGeoCoder.getFromLocation(context, lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1);

		final String address = makeAddress(fromLocation);

		// Log.d("way", address);
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