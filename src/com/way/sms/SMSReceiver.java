package com.way.sms;

import com.way.sms.handler.TextMessageHandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SMSReceiver extends BroadcastReceiver {
	
	public SMSReceiver(){
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Bundle bundle = intent.getExtras();
		if(bundle == null)return;
		
		makeWaySMS(bundle, context).handle();
	}

	private TextMessageHandler makeWaySMS(Bundle bundle, Context context) {
		return new WaySMSFactory().create((Object[])bundle.get("pdus"), context);
	}

}
