package com.techpalle.b24_activityresult;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ParentActivity extends ActionBarActivity {
	//declare all variables
	Button b1, b2;
	EditText et1, et2, et3;
	//step 1 : define all constants for child activities
	public static final int REQ_CD1 = 1;//this is for firstchildacti
	public static final int REQ_CD2 = 2;//this is for second child..
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parent);
		//initialize all variables
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		
		//step 2 : use startactivity for result
		//b1 -> for first activity
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent in = new Intent(ParentActivity.this, 
						FirstChildActivity.class);
				startActivityForResult(in, REQ_CD1);
			}
		});
		//b2 -> for second activity
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent in = new Intent(ParentActivity.this, 
						SecondChildActivity.class);
				startActivityForResult(in, REQ_CD2);
			}
		});
	}
	
	//step 4 : implement on activity result
	@Override
	protected void onActivityResult(int reqCode, int resCode, 
			Intent in) {
		//logic will go here
		if(reqCode == REQ_CD1){
			//this means results are from FIRST CHILD
			if(resCode == RESULT_OK){
				//first child success
				et1.setText("FIRST CHILD");
				et2.setText("SUCCESS");
				et3.setText("NO REASON");
			}else{
				//first child failure
				et1.setText("FIRST CHILD");
				et2.setText("FAILURE");
				Bundle bnd = in.getExtras();
				String reason = bnd.getString("reason");
				et3.setText(reason);
			}
		}else if(reqCode == REQ_CD2){
			//this means results are from SECOND CHILD
			if(resCode == RESULT_OK){
				//second child success
				et1.setText("SECOND CHILD");
				et2.setText("SUCCESS");
				et3.setText("NO REASON");
			}else{
				//second child failure
				et1.setText("SECOND CHILD");
				et2.setText("FAILURE");
				Bundle bnd = in.getExtras();
				String reason = bnd.getString("reason");
				et3.setText(reason);
			}
		}
		super.onActivityResult(reqCode, resCode, in);
	}
}
