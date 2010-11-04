package com.way.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

public class FacebookLogin extends Activity {

	private Facebook facebook = new Facebook();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final String applicationId = "4654b2da7a2844ac7e93c2c5091d13c2";
		facebook.authorize(FacebookLogin.this, applicationId, new String[] { "publish_stream", "read_stream", "offline_access" }, new DialogListener() {
			
			@Override
			public void onFacebookError(FacebookError e) {
				Log.d("WAY", "Facebook error");
				
			}
			
			@Override
			public void onError(DialogError e) {
				Log.d("WAY", "onError");
				
			}
			
			@Override
			public void onComplete(Bundle values) {				
				Log.d("WAY", "Facebook login successful");
				Toast.makeText(FacebookLogin.this, "Login successful", 300);
				
			}
			
			@Override
			public void onCancel() {
				Log.d("WAY", "on cancel");
			}
		});
	}
}
