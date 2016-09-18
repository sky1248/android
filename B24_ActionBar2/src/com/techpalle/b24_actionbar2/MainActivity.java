package com.techpalle.b24_actionbar2;

import java.util.ArrayList;

import android.support.v4.app.ActionBarDrawerToggle;
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
	ActionBar ab;
	DrawerLayout dl;
	TextView tv;
	ListView lv;
	ArrayList<String> al;
	ArrayAdapter<String> aa;
	
	//declare actionbar drawer toggle variable
	ActionBarDrawerToggle abdt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ab = getSupportActionBar();
		
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(getWallpaper());
		
		//set back ground for action bar
		//Drawable d = getResources().
			//	getDrawable(R.drawable.actionbarshape);
		//ab.setBackgroundDrawable(d);
		//initialize every thing
		dl = (DrawerLayout) findViewById(R.id.drawer_layout);
		tv = (TextView) findViewById(R.id.textView1);
		lv = (ListView) findViewById(R.id.left_drawer);
		al = new ArrayList<String>();
		
		al.add("Home");
		al.add("About us");
		al.add("Trainings");
		al.add("Contact us");
		al.add("Enquiry");
		al.add("Placements");
		
		aa = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, 
				al);
		lv.setAdapter(aa);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, 
					View view,
					int position, long id) {
				String item = al.get(position);
				tv.setText(item);
				dl.closeDrawer(lv);
				ab.setTitle(item);
			}
		});
		
		//intialize abdt
		abdt = new ActionBarDrawerToggle(this, 
				dl,
				R.drawable.ic_drawer, 
				R.drawable.ic_launcher, 
				R.drawable.ic_launcher){
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}
		};
		
		//set toggler listner to drawer layout
		dl.setDrawerListener(abdt);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		abdt.syncState();
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
		if(abdt.onOptionsItemSelected(item)){
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
