package com.example.smsblockerproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BlockSpecialMessage extends Activity {

	Button button_SpecialBlock,button_AbuseBlock,button_BackButton;
	EditText special_Number;
	String new_Number;
	DataBaseFacility dbf;
	static final int SPECIALID=5;
	static final int ABUSEWORDID=6;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	setContentView(R.layout.specialblockmsg);
	special_Number=(EditText) findViewById(R.id.specialNumber);
    button_SpecialBlock=(Button) findViewById(R.id.specialButton);
    button_AbuseBlock=(Button) findViewById(R.id.abuseButton);
    button_BackButton=(Button) findViewById(R.id.backButton);
    
    dbf=new DataBaseFacility(this);
    dbf.open();
    button_SpecialBlock.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new_Number=special_Number.getText().toString();
			dbf.insert(new_Number,SPECIALID);
			special_Number.setText("");
			special_Number.setHint("Enter Special Number/Word");
		}
	});
 button_AbuseBlock.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			new_Number=special_Number.getText().toString();
			dbf.insert(new_Number,ABUSEWORDID);
			special_Number.setText("");
			special_Number.setHint("Enter Special Number/Word");
		
		}
	});

 button_BackButton.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();		}
	});

	// TODO Auto-generated method stub
	}

}
