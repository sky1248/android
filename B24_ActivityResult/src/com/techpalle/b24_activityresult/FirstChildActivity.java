package com.techpalle.b24_activityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FirstChildActivity extends Activity {
	//declare all variables
	Button b1, b2;
	EditText et;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.firstchild);
	    // TODO Auto-generated method stub
	    //initialize all variables
	    b1 = (Button) findViewById(R.id.button1);
	    b2 = (Button) findViewById(R.id.button2);
	    et = (EditText) findViewById(R.id.editText1);
	    
	    //step 3 : use set result to pass data back to parent
	    b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	    //step 3 : use set result to pass data back to parent
	    b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent in = new Intent();
				in.putExtra("reason", et.getText().toString());
				setResult(RESULT_CANCELED, in);
				finish();
			}
		});
	}

}
