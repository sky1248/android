package com.example.smsblockerproject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OptionPage extends Activity {
Button sms_Button,call_Button,send_Sms;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.oprions);
	    sms_Button=(Button) findViewById(R.id.button1);
	    call_Button=(Button) findViewById(R.id.button2);
	    send_Sms=(Button)findViewById(R.id.button3);
	    sms_Button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent i=new Intent(getApplicationContext(),UserForm.class);
				startActivity(i);
				// TODO Auto-generated method stub
				
			}
		});
	
call_Button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				Intent i=new Intent(getApplicationContext(),CallBlockForm.class);
				startActivity(i);
				
			}
		});

send_Sms.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		
		Intent i=new Intent(getApplicationContext(),SendSms.class);
		startActivity(i);
		
		
		// TODO Auto-generated method stub
		
	}
});

	
	    
	    // TODO Auto-generated method stub
	}

}
