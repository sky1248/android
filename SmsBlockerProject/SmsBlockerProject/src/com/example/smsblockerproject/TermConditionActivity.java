package com.example.smsblockerproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TermConditionActivity extends Activity {
Button b;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	setContentView(R.layout.termsconditionfacility);
	b=(Button) findViewById(R.id.agree);
	b.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
		Intent i=new Intent(getApplicationContext(),MainActivity.class);
		startActivity(i);
		
		}
	});
	    // TODO Auto-generated method stub
	}

}
