package com.techpalle.actionbarexample;

import java.util.ArrayList;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	
	ListView lv;//dest
	ArrayList<String> al;//source
	ArrayAdapter<String> aa;
	DrawerLayout dl;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//get action bar
		ActionBar ab = getSupportActionBar();
		//display up carrot
		ab.setDisplayHomeAsUpEnabled(true);
		//how to apply background shape to action bar
		//get drawable
		Drawable d = getResources().getDrawable(R.drawable.actionbarshape);
		//set drawable object to ab action bar
		ab.setBackgroundDrawable(d);
		
		//Drawer layout work starts here
		lv = (ListView) findViewById(R.id.left_drawer);
		dl = (DrawerLayout) findViewById(R.id.drawer_layout);
		tv = (TextView) findViewById(R.id.textView1);
		
		al = new ArrayList<String>();
		al.add("Home");
		al.add("About us");
		al.add("Trainings");
		al.add("Enquiry");
		al.add("Placements");
		aa = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, 
				al);
		lv.setAdapter(aa);
		//implement lv click
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String item = al.get(position);
				tv.setText(item);
				dl.closeDrawer(lv);
			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		//write code to display drawer
		super.onPostCreate(savedInstanceState);
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
