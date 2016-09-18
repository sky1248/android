package com.techpalle.b24_databaseprogram;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	//DECLARE ALL VARIABLES
	EditText et1, et2, et3;
	Button b2, b4;
	MyDataBase mdb;
	//TableLayout tl;
	ListView lv;//DESTINATION
	SimpleCursorAdapter sca;//BRIDGE
	Cursor c; //SOURCE
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//INITIALIZE ALL VARIABLES
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		//tl = (TableLayout) findViewById(R.id.tableLayout1);
		lv = (ListView) findViewById(R.id.listView1);
		
		b2 = (Button) findViewById(R.id.button2);
		b4 = (Button) findViewById(R.id.button4);
		
		//create object for database file
		mdb = new MyDataBase(this);
		//open database
		mdb.open();
		//initialize cursor
		c = mdb.getTrainerDetails();
		//INITIALIZE ADAPTER
		//sca = new Simplecursorada
		sca = new SimpleCursorAdapter(this, 
				R.layout.row, 
				c, 
				new String[]{"_id","tname","tcourse","tsal"}, 
				new int[]{R.id.textView1, R.id.textView2, 
								R.id.textView3, R.id.textView4}, 
				SimpleCursorAdapter.NO_SELECTION);
		//SET ADAPTER TO LIST VIEWS
		lv.setAdapter(sca);
		//insert into trainer - on button 2
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = et1.getText().toString();
				String course = et2.getText().toString();
				String sal = et3.getText().toString();
				int isal = Integer.parseInt(sal);
				mdb.insertTrainer(name, course, isal);
				et1.setText("");et2.setText("");et3.setText("");
				et1.requestFocus();
				Toast.makeText(MainActivity.this, 
						"INSERTED ONE ROW", 0).show();
			}
		});
		//read from trainer - on button 4 - DISPLAY IN TABLE LAYOUT
		/*b4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Cursor c = mdb.getTrainerDetails();
				//NOW LET US READ DATA FROM CURSOR
				
				if(c != null){
					//there is some data
					while(c.moveToNext()){
						//CREATE A NEW ROW FOR EVERY LOOP
						TableRow tr = new TableRow(MainActivity.this);
						//PREPARE 4 TEXTVIEWS FOR 4 COLUMNS 
						TextView tv1 = new TextView(MainActivity.this);
						TextView tv2 = new TextView(MainActivity.this);
						TextView tv3 = new TextView(MainActivity.this);
						TextView tv4 = new TextView(MainActivity.this);
						//SET BACK GROUND FOR TEXT VIEWS
						tv1.setBackgroundResource(R.drawable.myshape);
						tv2.setBackgroundResource(R.drawable.myshape);
						tv3.setBackgroundResource(R.drawable.myshape);
						tv4.setBackgroundResource(R.drawable.myshape);
						//READ DATA FROM CURSOR
						int tno = c.getInt(0);
						String tname = c.getString(1);
						String tcourse = c.getString(2);
						int tsal = c.getInt(3);
						//SET COLUMN DATA INTO EACH TEXT VIEWS
						tv1.setText(""+tno);
						tv2.setText(tname);
						tv3.setText(tcourse);
						tv4.setText(""+tsal);
						//ADD ALL TEXTVIEWS TO TABLE ROW
						tr.addView(tv1);
						tr.addView(tv2);
						tr.addView(tv3);
						tr.addView(tv4);
						//NOW ADD YOUR TABLE ROW TO TABLE LAYOUT
						tl.addView(tr);
					}
				}
			}
		});*/

		//mdb.close();
	}
	@Override
	protected void onDestroy() {
		mdb.close();
		super.onDestroy();
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
