package com.way.xmpp;

import com.way.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class XMPPSetup extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		Button login = (Button) findViewById(R.id.login);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		EditText username = (EditText) findViewById(R.id.xmpp_username);
		EditText password = (EditText) findViewById(R.id.xmpp_password);

		Intent intent = new Intent();
		intent.setAction("com.way.xmpp.XMPP_SERVICE");
		
		intent.putExtra("username", username.getText().toString());
		intent.putExtra("password", password.getText().toString());
		
		this.startService(intent);
		
	}
		
}
