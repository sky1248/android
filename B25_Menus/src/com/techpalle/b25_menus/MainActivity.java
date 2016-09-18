package com.techpalle.b25_menus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	//step 2 : load menu xml from activity java file through
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	//step 3 : handle menu item clicks using 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
		case R.id.item1://trainings
		case R.id.item2: //call
		case R.id.item3:
		case R.id.item4:
		case R.id.item5:
		case R.id.item6://sub menu item
		case R.id.item7:
		case R.id.item8:
			Toast.makeText(this, "you clicked "+item.getTitle(), 0).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
