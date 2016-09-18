package com.sravani.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Homeact1 extends Activity 
{

	
	  
	Button b1,b2;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.home);
	    b1=(Button) findViewById(R.id.button1);
	    b2=(Button) findViewById(R.id.button2);
	    
	    b1.setOnClickListener(new OnClickListener()
	    {
			
			@Override
			public void onClick(View v)
			{
				Intent j=new Intent(getApplicationContext(),Personalact.class);
				startActivity(j);
				
			}
		});
	    
	    b2.setOnClickListener(new OnClickListener()
	    {
			
			@Override
			public void onClick(View v)
			{
				Intent i=new Intent(getApplicationContext(), Register1.class);
				startActivity(i);
				
			}
		});
	}	    

}
