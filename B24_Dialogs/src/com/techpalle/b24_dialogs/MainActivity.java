package com.techpalle.b24_dialogs;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	//step1 : define constants for dialogs
	TextView tv;
	
	public static final int SMPL_ALRT_DLG = 1;//for simple alert dialog
	public static final int PRGRS_DLG = 2;//for progress dialog
	public static final int DT_PKR_DLG = 3;//for date picker dialog
	public static final int GMAIL_CSTM_DLG = 4; //for custom dialog for gmail
	
	DatePickerDialog.OnDateSetListener ond = 
			new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, 
				int monthOfYear,
				int dayOfMonth) {
			tv.setText(year+":"+monthOfYear+":"+dayOfMonth);
			showDialog(GMAIL_CSTM_DLG);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView)findViewById(R.id.textView1);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative1);
		//step 2 : show simple alert dialog first
		
		//below code will create a button dynamically
		Button b = new Button(this);
		b.setText("New Button");
		//b.setLayoutParams(new LayoutParams(LayoutParams., height));;
		//now add this button to rl
		rl.addView(b);
	
		showDialog(SMPL_ALRT_DLG);
	}

	//step 3 : implement oncreate dialog
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id){
		case SMPL_ALRT_DLG:
			//this is logic for creating simple alert dialog
			AlertDialog.Builder ab = new AlertDialog.Builder(this);
			ab.setTitle("My First Dialog");
			ab.setIcon(R.drawable.ic_launcher);
			ab.setMessage("Do you want to proceed?");
			ab.setPositiveButton("Yes", new OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					showDialog(PRGRS_DLG);
				};
			});
			ab.setNegativeButton("No", null);
			return ab.create();
			//break;
			
		case PRGRS_DLG:
			//this is logic for creating progress dialog
			ProgressDialog p = new ProgressDialog(this);
			p.setTitle("Progressing");
			//p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			
			p.setButton(ProgressDialog.BUTTON_POSITIVE, 
					"Yes", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							showDialog(DT_PKR_DLG);
						}
					});
			p.setButton(ProgressDialog.BUTTON_NEGATIVE, 
					"No", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					});
			return p;
			//break;
			
		case DT_PKR_DLG:
			//this is the logic for creating date picker dialog
			DatePickerDialog dp = new DatePickerDialog(this, ond, 
					2015, 0, 28);
			return dp;
			//break;
		case GMAIL_CSTM_DLG:
			//this is the logic for creating custom gmail dialog
			AlertDialog.Builder ab2 = new AlertDialog.Builder(this);
			//let us load xml design throug layout inflator
			View v = getLayoutInflater().inflate(R.layout.dialogs, null);
			//set that xml to builder
			ab2.setView(v);
			//declare variable for et1, et2;
			final EditText et1, et2;
			et1 = (EditText) v.findViewById(R.id.editText1);
			et2 = (EditText) v.findViewById(R.id.editText2);
			
			ab2.setPositiveButton("Ok", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//get user name, pw and display on the textview
					String uname = et1.getText().toString();
					String pw = et2.getText().toString();
					tv.setText("USER NAME:"+uname+"   PASSWORD:"+pw);
					
				}
			});
			ab2.setNegativeButton("No", null);
			return ab2.create();
			//break;
		}
		return super.onCreateDialog(id);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
