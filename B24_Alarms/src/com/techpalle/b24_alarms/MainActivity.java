package com.techpalle.b24_alarms;

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	Button b;
	AlarmManager am; //use this class to set some alarms
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//initialize every thing
		am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//now set alarm which will trigger after 1 minute
				Intent in = new Intent(MainActivity.this, 
						MainActivity.class);
				PendingIntent pi = PendingIntent.getActivity(
						MainActivity.this, 0, in, 0);
				
				/*am.setExact(AlarmManager.RTC_WAKEUP, 
						System.currentTimeMillis()+5000, 
						pi);*/
				am.setRepeating(AlarmManager.RTC_WAKEUP, 
						System.currentTimeMillis()+60000, 
						5000, pi);
			}
		});
		
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
