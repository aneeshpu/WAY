/**
 * 
 */
package com.way.sms;

import java.util.regex.Pattern;

public class WayRequest{
	
	private static final Pattern WAY_MESSAGE_FORMAT = Pattern.compile("[wW]here *[aA]?r?e? *[yY]?o?u? *[\\p{Punct}]*|WAY");
	
	public boolean isWayRequest(String message){
		return WAY_MESSAGE_FORMAT.matcher(message).matches();
	}
	
}