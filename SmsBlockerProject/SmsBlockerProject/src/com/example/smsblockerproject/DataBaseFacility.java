package com.example.smsblockerproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBaseFacility {
 
	Context mycon;
	SQLiteDatabase sdb;
	DataBaseHelper dbh;
	public DataBaseFacility(Context context) {
		mycon=context;
		dbh=new DataBaseHelper(mycon,"MyProjectDb7",null,1);
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
	
	public void insert(String name,String number,String pic,int id)
	{
			ContentValues cv=new ContentValues();
			String mynumber=number;
		    int length=number.length();
		    if(number.length()<10 || number.length()>13 )
		    {
		    	if(id==4)
		    	{}
		    	else
		    	{
		    	Toast.makeText(mycon, "Enter Valid Number", 0).show();
		    	}
		    }
		    else 
		    {
		    
		    	if(number.length()==10)
		    	{
		    		mynumber="+91"+number;
		    	}
		    	if(number.startsWith("0"))
		    	{
		    		mynumber=number.replaceFirst("0","+91");	
		    	}
		    	    		
			    if(id==1)
			    {
			    	cv.put("Name",name);
			    	cv.put("Number",mynumber);
			    	cv.put("pic", pic);
			    	sdb.insert("SMSBlocks",null, cv);
			    	Toast.makeText(mycon, "Number Added to SMS Blocked List successfully", 0).show();
			    }
			    if(id==2)
			    {
			    	cv.put("Name",name);
			    	cv.put("Number",mynumber);
			    	cv.put("pic", pic);
			    	sdb.insert("CALLBlocks",null, cv);
			    	Toast.makeText(mycon, "Number Added to CALL Blocked List successfully", 0).show();
		        }
			    if(id==3)
			    {
			    	
					cv.put("Name",name);
					cv.put("Number",mynumber);
					sdb.insert("MOBILEContacts",null, cv);
				    	
			    }
			    
			    
			   
	
		    }
		    
		    if(id==4)
		    {
		    	String Number=name;
		    	String DataMessage=number;
				String date=pic;
		    	cv.put("Name",Number);
				cv.put("Number",DataMessage);
				cv.put("Date", date);
				
				sdb.insert("INCOMING_Message",null, cv);
				Toast.makeText(mycon, "Message is stored", 0).show();	
	    	}
		}
	
	public void insert(String new_Number,int id) {
		// TODO Auto-generated method stub
		ContentValues cv=new ContentValues();
		if(id==5)
		{
			cv.put("Number", new_Number);
			
			sdb.insert("SMSBlocks",null, cv);
			Toast.makeText(mycon, "Number Added to SMS Blocked List successfully", 0).show();
		}
		
		if(id==6)
		{
			cv.put("Abuses", new_Number);
			
			sdb.insert("BLOCK_Abuses",null, cv);
			Toast.makeText(mycon, "Wrong Word has Been Added to SMS Blocked List successfully", 0).show();
		}
		
	}
	public Cursor traverseAll(int ID)
	{
		Cursor c=null;
		if(ID==1)
		{
			c=sdb.query("SMSBlocks",null, null, null, null, null, null);	
		}
		if(ID==2)
		{
			c=sdb.query("CALLBlocks",null, null, null, null, null, null);	
		}
		if(ID==3)
		{
			c=sdb.query("MOBILEContacts",null, null, null, null, null, null);	
		}
		if(ID==4)
		{
			c=sdb.query("INCOMING_Message",null, null, null, null, null, null);	
		}
		if(ID==6)
		{
			c=sdb.query("BLOCK_Abuses",null, null, null, null, null, null);	
		}
	
		
		return c;
		
	}
	
	public Cursor traverseNumber(int CALLID) {
		Cursor c=sdb.query("CALLBlocks", new String[]{"Number"}, null, null, null, null, null,null);
		// TODO Auto-generated method stub
		return c;
	}
	
	public void update(String name,String number,String old_Number,String pic,int ID)
	{
		ContentValues cv=new ContentValues();
		if(ID==1)
		{
			cv.put("Name",name);
			cv.put("Number",number);
			cv.put("pic", pic);
			sdb.update("SMSBlocks",cv, "Number=?",new String[]{String.valueOf(old_Number)});
			Toast.makeText(mycon, "SMS record Updated successfully", 0).show();
		}
		if(ID==2)
		{
			cv.put("Name",name);
			cv.put("Number",number);
			cv.put("pic", pic);
			sdb.update("CALLBlocks",cv, "Number=?",new String[]{String.valueOf(old_Number)});
			Toast.makeText(mycon, "CALL record Updated successfully", 0).show();
		}
		if(ID==3)
		{
			cv.put("Name",name);
			cv.put("Number",number);
			cv.put("pic", pic);
			sdb.update("MOBILEContacts",cv, "Number=?",new String[]{String.valueOf(number)});
			Toast.makeText(mycon, "record Updated successfully", 0).show();
		}
		
	}
	
	
	public void remove(String contact,int ID)
	{
		if(ID==1)
		{
			sdb.delete("SMSBlocks","Number=?", new String[]{String.valueOf(contact)});
		}
		if(ID==2)
		{
			sdb.delete("CALLBlocks","Number=?", new String[]{String.valueOf(contact)});
		}
		if(ID==3)
		{
			sdb.delete("MOBILEContacts",null, null);
		}
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
			db.execSQL("create table SMSBlocks(_id Integer primary key, Name text," +
					"Number text,pic text);");
				
			db.execSQL("create table CALLBlocks(_id Integer primary key, Name text," +
					"Number text,pic text);");
				
			db.execSQL("create table MOBILEContacts(_id Integer primary key, Name text," +
					"Number text);");			
	
			db.execSQL("create table INCOMING_Message(_id Integer primary key, Name text," +
					"Number text,Date text);");			
			db.execSQL("create table BLOCK_Abuses(_id Integer primary key, Abuses text);");			


		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}



	

	

}
