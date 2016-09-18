package com.techpalle.b24_listview;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	//step 2 : declare all variables
	EditText et;
	Button b;
	ListView lv; //destination
	
	ArrayList<String> al; //source
	ArrayAdapter<String> aa; //bridge or adapter
	/*
	 * [listview] <-- [ArrayAdapter] <-- [ArrayList] <-- programmer
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//step 3 : initialize all variables
		et = (EditText) findViewById(R.id.editText1);
		b = (Button)findViewById(R.id.button1);
		lv = (ListView) findViewById(R.id.listView1);
		
		al = new ArrayList<String>();
		//step 4 : establish communication between src-adapter
		aa = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, al);
		
		//step 5 : establish communication between adapter- dest
		lv.setAdapter(aa);
		//step 6 : implement button click listener
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//6.i take data from edit text
				String item = et.getText().toString();
				//6.ii add above item to array list
				al.add(item);
				//6.iii when source changes notify adapter
				aa.notifyDataSetChanged();
				
				//6.iv clean edit text and put cursor in edit text
				et.setText("");
				et.requestFocus();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
