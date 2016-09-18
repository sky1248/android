package com.techpalle.b24_intentdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	TextView tv;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second);
	    tv = (TextView) findViewById(R.id.textView1);
	    
	    // TODO Auto-generated method stub
	    Intent in = getIntent();//this opens intent passed by prev scrn
	    Bundle bnd = in.getExtras();
	    int i = bnd.getInt("i-value");
	    
	    String old = tv.getText().toString();
	    
	    tv.setText(old+"..."+i);
	}

}
