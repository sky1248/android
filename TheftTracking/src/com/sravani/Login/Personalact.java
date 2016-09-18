package com.sravani.Login;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class Personalact extends Activity
{

	ArrayList<String> al;
	ArrayAdapter<String> aa;
	MyDb td;
	Button b1;
	EditText et1,et2;
	Spinner s1,s2;
	CheckBox ch1,ch2;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.personal);
	   
	
	     et1=(EditText) findViewById(R.id.editText1);
	     et2=(EditText) findViewById(R.id.editText2);
	     
	     b1=(Button) findViewById(R.id.button1);
	     
	     ch1=(CheckBox) findViewById(R.id.checkBox1);
	     ch2=(CheckBox) findViewById(R.id.checkBox2);
	     
	     s1=(Spinner) findViewById(R.id.spinner1);
	     s2=(Spinner) findViewById(R.id.spinner2);
	     
	     al=new ArrayList<String>();
	      al.add("9999999999");
	      al.add("9111111111");
	      al.add("9955555555");
	aa=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,al);
		    s1.setAdapter(aa);
		    aa.notifyDataSetChanged();
	    
	     
	     td=new MyDb(this);
	     td.open();
	     td.close();
	     
	     
	    
	}

}
