package com.sravani.Login;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register1 extends Activity 
{

	int count=0;
	MyDb tdb;
	EditText name,pwd,email,gpw,imei;
	Button b1;
	String s1,s2,s3,s4,s5;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        b1=(Button) findViewById(R.id.button1);
        name=(EditText) findViewById(R.id.editText1);
        pwd=(EditText) findViewById(R.id.editText2);
        email=(EditText) findViewById(R.id.editText3);
        gpw=(EditText) findViewById(R.id.editText4);
        imei=(EditText) findViewById(R.id.editText5);  
        
        tdb=new MyDb(this);
        tdb.open();
        
        
        
        b1.setOnClickListener(new OnClickListener()
        {
			
			@Override
			public void onClick(View v) 
			{
				s1=name.getText().toString();
				s2=pwd.getText().toString();
		        s3=email.getText().toString();
		        s4=gpw.getText().toString();
		        s5=imei.getText().toString();
		        
		        Cursor c=tdb.getTrainer();
			    if(c!=null)
			    {
			         while(c.moveToNext())
			          {	
			    	       String str1=c.getString(2);
			    	       String str2=c.getString(3);
			    	
			    	        if(s2.equals(str1)&&s3.equals(str2) )
			    	        {
			    		          count++;
			    	        }
			    	
			    
			          }
			    }
			    
			    if(count>0)
			    {
			    	Toast.makeText(getApplicationContext(), 
			    			"already registered enter new ", 0).show();
			    	
			    }
			    if(count==0)
			    {
			    	ContentValues cv=new ContentValues();
				    cv.put("name",s1);
				    cv.put("password",s2);
				    cv.put("email", s3);
				    cv.put("gpw",s4);
				    cv.put("imei",s5);
				    
				    tdb.insert(cv);
			    }  
					
			    Toast.makeText(getApplicationContext(), "INSERTED IN DB", 0).show();
					  	name.setText("");
					  	pwd.setText("");
					    email.setText("");
					    gpw.setText("");
					    imei.setText("");
			    	
			    
			    
			    
			}
			
		});
       
        
        
    }

}
