package com.example.smsblockerproject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSms extends Activity {
Button send,back,exit;
EditText e,e1,e2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sendsms);
	    send=(Button) findViewById(R.id.button1);
	    back=(Button) findViewById(R.id.button2);
	    exit=(Button) findViewById(R.id.button3);
	    e=(EditText) findViewById(R.id.editText1);
	    e1=(EditText) findViewById(R.id.editText2);
	  
	    send.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String number=e.getText().toString();
				String message=e1.getText().toString();
				Intent i=new Intent(getApplicationContext(),SendSms.class);
				PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0,i, 0);
				SmsManager SMS1 = SmsManager.getDefault();
		        SMS1.sendTextMessage(number, null, message, null, null);
		Toast.makeText(getApplicationContext(),"Message sending..", 0).show();
			}
		});
	    
	    back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			finish();	
			}
		});
	    exit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent i=new Intent(getApplicationContext(),MainActivity.class);
				finish();	
			}
		});
	    // TODO Auto-generated method stub
	}

}
