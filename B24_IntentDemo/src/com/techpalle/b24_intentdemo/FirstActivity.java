package com.techpalle.b24_intentdemo;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends ActionBarActivity {
	Button b; //declare variable
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		//initialize all variables
		b = (Button) findViewById(R.id.button1);
		//write button click listener
		b.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(FirstActivity.this, 
						SecondActivity.class);
				//pass some i value 10
				int i = 11;
				in.putExtra("Hello",i);
				startActivity(in);
			}
		});
	}
}
