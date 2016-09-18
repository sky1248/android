package com.techpalle.b24_actionbars;
//v7 library is support library
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//first get action bar
		ActionBar ab = getSupportActionBar();
		//on above action bar display up carrot
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setBackgroundDrawable(getWallpaper());
		//apply some back ground to action bar
		Drawable d = 
				getResources().getDrawable(R.drawable.actionbarshape);
		ab.setBackgroundDrawable(d);
		//set logo on action bar
		//1. create one imageview dynamically
		ImageView iv = new ImageView(this);
		//2. apply your company logo image.png onto above image view
		iv.setImageResource(R.drawable.action_image);
		//3. pass image view to ab
		ab.setCustomView(iv);
		//4. set flags as we are customizing action bar
		ab.setDisplayOptions
		(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
		
	}

	//creating or preparing your action bar
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
		switch(id){
		case R.id.action_call://call
			Toast.makeText(this, "call", 0).show();
			break;
		case R.id.action_cancel://cancel
			Toast.makeText(this, "cancel", 0).show();
			break;
		case R.id.action_copy://copy
			Toast.makeText(this, "copy", 0).show();
			break;
		case R.id.action_cut://cut
			Toast.makeText(this, "cut", 0).show();
			break;
		case R.id.action_settings:
			Toast.makeText(this, "settings", 0).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
