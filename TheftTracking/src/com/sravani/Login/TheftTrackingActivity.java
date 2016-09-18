package com.sravani.Login;

import android.app.Activity;

import android.content.Intent;

import android.database.Cursor;

import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;

import android.widget.EditText;



public class TheftTrackingActivity extends Activity 
{

	EditText et1;

	String s1;

	Button b1;

	MyDb tdb;

	@Override

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		et1=(EditText) findViewById(R.id.editText1);

		b1=(Button) findViewById(R.id.button1);


		tdb=new MyDb(this);

		tdb.open();


		b1.setOnClickListener(new OnClickListener()

		{


			@Override

			public void onClick(View v) 
{


				s1=et1.getText().toString();

				Cursor c=tdb.getTrainer();

				if(c!=null)
{

					while(c.moveToNext())

					{   	   
					String str1=c.getString(2);



					if(s1.equals(str1))
						{
		Intent i=new Intent(getApplicationContext(),Homeact1.class);

				startActivity(i);
				}

				if(s1!=str1)    {


				finish();
						}

					}

				}




			}


		});



	}

}