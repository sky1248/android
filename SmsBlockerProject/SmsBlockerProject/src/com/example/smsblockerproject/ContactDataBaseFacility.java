package com.example.smsblockerproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ContactDataBaseFacility {
 
	Context mycon;
	SQLiteDatabase sdb;
	DataBaseHelper dbh;
	public ContactDataBaseFacility(Context context) {
	
		mycon=context;
		dbh=new DataBaseHelper(mycon,"MyProjectDb3",null,1);
		// TODO Auto-generated constructor stub
	}
	public void open()

	{
	    sdb=dbh.getWritableDatabase();	
	}
	public void close()
	{
		sdb.close();
	}
	
	public void insert(String name,String number)
	{
		
			ContentValues cv=new ContentValues();
			cv.put("Name",name);
			cv.put("Number",number);
			
			
			sdb.insert("mobileContacts",null, cv);
			
    	
	}
	
	public Cursor traverseAll()
	{
	Cursor c=sdb.query("mobileContacts",null, null, null, null, null, null);	
		return c;
		
	}
	
	public Cursor search(String id)
	{
	Cursor c=sdb.query("Blocks", null, "id=?", new String[]{id}, null, null, null);	
		
	return c;
		
	}
	
	public void update(int  id,String name,String number)
	{
		ContentValues cv=new ContentValues();
		cv.put("Name",name);
		cv.put("Number",number);
		//cv.put("pic", pic);
		
		
		sdb.update("mobileContacts",cv, "_id=?",new String[]{String.valueOf(id)});
		Toast.makeText(mycon, "record Updated successfully", 0).show();
			
		
	}
	
	
	public void delete()
	{
		sdb.delete("mobileContacts", null,null);
	}
	
	private class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//db.execSQL("create table BlockList(_id Integer primary key,mynumber text);");
			db.execSQL("create table mobileContacts(_id Integer primary key, Name text," +
					"Number text);");
			
				
				
						
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
