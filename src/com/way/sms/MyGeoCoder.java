package com.way.sms;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class MyGeoCoder {

	public List<Address> getFromLocation(Context context, double latitude, double longitude, int maxResults) {
		try {
			return new Geocoder(context).getFromLocation(latitude, longitude, maxResults);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
