package com.techpalle.adapterdemo1;


import java.util.ArrayList;

import java.util.Iterator;




import android.os.Bundle;

import android.app.Activity;

import android.app.AlertDialog;

import android.app.AlertDialog.Builder;

import android.app.Dialog;

import android.content.DialogInterface;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ListView;

import android.widget.Toast;

import android.support.v4.app.NavUtils;


public class CityActivity extends Activity {

	
EditText et;
	
Button add,showall,delete;
	
ListView lv;
	
ArrayList<String> al;
	
ArrayAdapter<String> aa;
	
public static final int ITM_ALRT_DLG=1;

	public static final int CSTM_DLG=2;
	
CharSequence[] cs;
	
int j;
    
@Override
    
public void onCreate(Bundle savedInstanceState) {
       
 super.onCreate(savedInstanceState);
        
setContentView(R.layout.activity_city);
      
  et=(EditText) findViewById(R.id.editText1);
        
add=(Button) findViewById(R.id.button1);
        
showall=(Button) findViewById(R.id.button2);
        
delete=(Button) findViewById(R.id.button3);
        
lv=(ListView) findViewById(R.id.listView1);
        
al=new ArrayList<String>();
        
aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al);
        
lv.setAdapter(aa);
        
       
 add.setOnClickListener(new OnClickListener() {
			
			
public void onClick(View v) {
				
String s=et.getText().toString();
				
al.add(0, s);
				
aa.notifyDataSetChanged();
				
Toast.makeText(CityActivity.this,"congratulation your city "+s+" is entered sucessfully", Toast.LENGTH_LONG).show();	
			
}
	
	});
        
showall.setOnClickListener(new OnClickListener() {
			
			
public void onClick(View v) {
				
showDialog(ITM_ALRT_DLG); 
				
				
			
}
		
});
        
delete.setOnClickListener(new OnClickListener() {
			
			
public void onClick(View v) {
				
showDialog(CSTM_DLG);
		
	}
		
});
       
 
    }
   
 @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
    	
// TODO Auto-generated method stub
  
  	switch(id)
    	{
    	
case ITM_ALRT_DLG:
    		
Toast.makeText(CityActivity.this, "size of arraylist"+al.size(), Toast.LENGTH_LONG).show();
    		
AlertDialog.Builder ab=new AlertDialog.Builder(this);
    		
ab.setTitle("List of Cities");
    		
//It converts the arraylist<string> to charSeuence[]
    		
cs=al.toArray(new CharSequence[al.size()]);
    	
	ab.setItems(cs,null);
    		
return ab.create();

    	case CSTM_DLG:
    		
AlertDialog.Builder ab1=new AlertDialog.Builder(this);
    		
View view=getLayoutInflater().inflate(R.layout.dialog, null);
    		
ab1.setTitle("City information");
    		
Button b=(Button) view.findViewById(R.id.button1);
    		
final EditText et=(EditText) view.findViewById(R.id.editText1);
    
		b.setOnClickListener(new OnClickListener() {
				
				
public void onClick(View v) {
					
String s=et.getText().toString();
					
if(al.contains(s))
 {
						
al.remove(s);
						
aa.notifyDataSetChanged();
			
		}
					
else
						
Toast.makeText(CityActivity.this,s+" is not available in the list", Toast.LENGTH_LONG).show();
					
// TODO Auto-generated method stub
					
				
}
			
});
 
 ab1.setView(view);
               
return ab1.create();    
    		
    	
}
    	
return super.onCreateDialog(id);
    
}

    

    

}
