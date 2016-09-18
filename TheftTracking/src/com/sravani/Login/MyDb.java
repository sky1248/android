package com.sravani.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;



public class MyDb
{
	public static String MY_DB="theft.db";
    Context mycon;
    MyHelper mh;
    SQLiteDatabase sdb;
    public MyDb(Context c)
    {
    	mycon=c;
    	mh=new MyHelper(mycon,MY_DB,null,1);
    }
    public void open()
    {
    	sdb=mh.getWritableDatabase();
    }
    public void close()
    {
    	sdb.close();
    }
    public void insert(ContentValues cv)
    {
    	sdb.insert("theft", null,cv);
        	
    }
    public void insert1(ContentValues cv1)
    {
	sdb.insert("Personaldb", null,cv1);

    }
    
    public Cursor getTrainer()
    {
    	Cursor c=sdb.query("theft",null,null,null,null,null,null);
         return c;
    }
    
   public Cursor getTrainer1() 
    {
		Cursor c= sdb.query("Personaldb",null,null,null,null,null,null);
		return c;

	}
   
	
    public class MyHelper  extends SQLiteOpenHelper
    {
      	

		public MyHelper(Context context, String name, CursorFactory factory,
				int version)
		{
		super(context, name, factory, version);			
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL("create table theft(_id integer primary key," +
					"name text,password text,email text,gpw text,imei integer)");
					
			db.execSQL("create table Personaldb(_id integer primary key," +
					"ppno integer,spno integer,loc text,calllogs text,photos text)");
					
					
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
				
			
		}
    }
}
