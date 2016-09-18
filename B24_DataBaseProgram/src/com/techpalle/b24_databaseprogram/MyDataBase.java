package com.techpalle.b24_databaseprogram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//step 0 : design your db on the paper
//step 1 : CREATE SAPEARATE JAVA FILE FOR DATABASE PROGRAM
public class MyDataBase {
	/*
	 * data base name : techpalle
	 * vno : 1
	 * 2 tables : Student(_id int pk, sname text...)
	 *            Trainer(_id int pk, tname text...)
	 */
	/*
	 * IF YOU ARE THE USER OF THIS FILE:
	 * a. first create object for this class
	 * b. call open()
	 * c. now you can insert, read, write, delete...
	 * d. at the end close()
	 */
	//STEP 4 : DECLARE ALL VARIABLES
	MyHelper mh;
	SQLiteDatabase sdb;
	//STEP 5 : CREATE OBJECT FOR HELPER
	public MyDataBase(Context con){
		//mh = new MyHelper(con, "techpalle", null, 1);
		//FOR SECOND VERSION
		//mh = new MyHelper(con, "techpalle", null, 2);//increment db-v
		//for third version
		//mh = new MyHelper(con, "techpalle", null, 3);//increment db-v
		mh = new MyHelper(con, "techpalle", null, 1);//increment db-v
	}
	//STEP 6 : CREATE OBJECT FOR SQLITEDATABASE
	public void open(){
		sdb = mh.getWritableDatabase();
	}
	//STEP 7 : DO ALL DML OPERATIONS HERE
	//7.A : INSERT INTO STUDENT TABLE
	public void insertStudent(String name, String crs){
		ContentValues cv = new ContentValues();
		cv.put("sname", name);
		cv.put("scourse", crs);
		sdb.insert("Student", null, cv);
	}
	//7.B : INSERT INTO TRAINER
	public void insertTrainer(String name, String crs, int sal){
		ContentValues cv = new ContentValues();
		cv.put("tname", name);
		cv.put("tcourse", crs);
		cv.put("tsal", sal);
		sdb.insert("Trainer", null, cv);
	}
	//7.c : READ FROM STUDENT TABLE
	public Cursor getStudentDetails(){
		Cursor c = null;
		c = sdb.query("Student", null, null, null, null, null, null);
		return c;
	}
	//7.d : READ FROM TRAINER TABLE
	public Cursor getTrainerDetails(){
		Cursor c = null;
		c = sdb.query("Trainer", null, null, null, null, null, null);
		return c;

		//these all different methods to get details from tables
		/*//QUERY1 : RETRIEVE TRAINER DETAILS WHOSE NO IS 2
		c = sdb.query("Trainer", null, "_id = 2", 
				null, null, null, null);
		// [OR]
		c = sdb.query("Trainer", null, "_id = ?", new String[]{"2"}, 
				null, null, null);
		//QUERY2 : RETRIEVE COMPLETE DETALS OF STEVE
		c = sdb.query("Trainer", null, "tname = 'Steve'", 
				null, null, null, null);
		// [OR]
		c = sdb.query("Trainer", null, "tname = ?", 
				new String[]{"Steve"}, 
				null, null, null);
		//QUERY3 : RETRIEVE ALL TRAINER DETAILS WHOSE SALARY IS 10000
		c = sdb.query("Trainer", null, "tsal = ?", 
				new String[]{"10000"},
				null, null, null);
		//QUERY4 : RETRIEVE ALL TRAINERS WHOSE SALARIES ARE LESS THAN 10000
		c = sdb.query("Trainer", null, 
				"tsal < ?", new String[]{"10000"}, null, null, null);
		//QUERY5 : RETRIEVE ALL TRAINERS WHOSE NAME STARTS WITH 'S'
		c = sdb.query("Trainer", null, 
				"tname LIKE ?", 
				new String[]{"S%"}, null, null, null);
		//QUERY6 : RETRIEVE ALL TRAINERS WHOSE NAME ENDS WITH 'h'
		c = sdb.query("Trainer", null, 
				"tname LIKE ?", 
				new String[]{"%h"}, null, null, null);
		//QUERY7 : RETRIEVE ALL , WHOSE SAL >5K AND NAME ENDS WITH 'h'
		c = sdb.query("Trainer", null, 
				"tsal > ? AND tname LIKE ?", 
				new String[]{"5000","%h"}, null, null, null);
		//QUERY8 : RETRIEVE ALL TRAINERS WHO teaches .NET OR WHOSE SAL>6K
		c = sdb.query("Trainer", null, 
				"tcourse = ? OR tsal > ?", 
				new String[]{".net","6000"}, null, null, null);
		//QUERY9 : GET TRAINER NAME OF ANDROID COURSE
		c = sdb.query("Trainer", new String[]{"tname","tsal"}, 
				"tcourse = ?", new String[]{"Android"}, 
				null, null, null);*/
		
	}
	//UPDATE SATISH SALARY TO 20000
	/*public void updateTrainer(){		
		ContentValues cv = new ContentValues();
		cv.put("tsal",20000);
		sdb.update("Trianer", cv, "tname = ?", new String[]{"Satish"});
	}
	public void updateTrainerCourse(){
		//UPDATE STEVE COURSE TO PHP
		ContentValues cv = new ContentValues();
		cv.put("tcourse", "php");
		sdb.update("Trainer", cv, "tname = ?", new String[]{"Steve"});
		//UPDATE BILL NAME TO BILL GATES
		ContentValues cv1 = new ContentValues();
		cv1.put("tname","Bill Gates");
		sdb.update("Trainer", cv1, "tname = ?", new String[]{"Bill"});
		//UPDATE LOK'S & SATISH'S SALARY TO 30000
		ContentValues cv2 = new ContentValues();
		cv2.put("tsal", 30000);
		sdb.update("Trainer", cv2, 
				"tname = ? OR tname = ?", 
				new String[]{"Satish", "Lok"});
		//UPDATE SATISH COURSE TO JAVA, AND SALARY TO 15000
		ContentValues cv3 = new ContentValues();
		cv3.put("tcourse", "java");
		cv3.put("tsal", "15000");
		sdb.update("Trainer", cv3, "tname = ?", new String[]{"Satish"});
		
		//UPDATE .NET TRAINERS SALARY TO THE DOUBLE
		//1. GET ID AND SALARY OF ALL .NET TRAINERS
		Cursor c = sdb.query("Trainer", new String[]{"_id","tsal"}, 
				"tcourse = ?", new String[]{".net"}, 
				null, null, null);
		if(c != null){
			while(c.moveToNext()){
				int id = c.getInt(c.getColumnIndex("_id"));
				int sal = c.getInt(c.getColumnIndex("tsal"));
				int doublesal = sal*2;
				ContentValues cv4 = new ContentValues();
				cv4.put("tsal", doublesal);
				//2. NOW UPDATE SALARY BASED ON ID
				sdb.update("Trainer", cv4, "_id = ?", 
						new String[]{""+id});
			}
		}
		
		//CONVERT JAVA-TO-.NET , .NET-TO-JAVA
		
		Cursor c1 = sdb.query("Trainer", null, null, null, null, null, null);
		if(c1 != null){
			while(c1.moveToNext()){
				int id = c1.getInt(c1.getColumnIndex("_id"));
				String crs = c1.getString(c1.getColumnIndex("tcourse"));
				ContentValues cvv1 = new ContentValues();
				
				if(crs.equals("java")){
					//UPDATE TO .NET
					cvv1.put("tcourse", ".net");
					sdb.update("Trainer", cvv1, "id = ?", 
							new String[]{""+id});
				
				}else if(crs.equals(".net")){
					//UPDATE TO JAVA
					cvv1.put("tcourse", "java");
					sdb.update("Trainer", cvv1, "id = ?", 
							new String[]{""+id});
				}
			}
		}
		
		//DELETE ALL TRAINERS WHOSE SAL < 10000
		sdb.delete("Trainer", "tsal < ?", new String[]{"10000"});
		//DELETE ALL TRAINER ROWS
		sdb.delete("Trainer", null, null);
		
	}*/
	
	//STEP 7 : END OF DML OPERATIONS
	
	//STEP 2 : PERFORM DDL OPERATIONS USING INNER CLASS - HELPER
	private class MyHelper extends SQLiteOpenHelper{
		public MyHelper(Context context, String name, 
				CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			//STEP3 : CREATE ALL YOUR TABLES HERE
			db.execSQL("create table Student(_id integer primary key, "
					+ "sname text, scourse text);");
			db.execSQL("create table Trainer(_id integer primary key, "
					+ "tname text, tcourse text, tsal integer);");
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, 
				int oldVersion, int newVersion) {
			//ALL FUTURE DB UPGRADATIONS HAS TO BE DONE HERE.
			
			switch(newVersion){
			case 2:
				//for second version, we are adding pf column
				db.execSQL("alter table Trainer add column pf integer;");
				break;
			case 3:
				//for third version, we are adding sgrade column to student
				db.execSQL("alter table Student add column sgrade text;");
				break;
			case 4:
				//for 4th version
				//remove trainer table
				
				db.execSQL("drop table Trainer if exists;");
				//remove sgrade column from student table
				db.execSQL("alter table Student drop column sgrade;");
				//create new employee table eno, ename, esal
				db.execSQL("crete table Emp(_id integer primary key, "
						+ "ename text, esal integer);");
				break;
			}
		}
	}
	//STEP 8 : CLOSE DATABASE
	public void close(){
		sdb.close();
	}
}
