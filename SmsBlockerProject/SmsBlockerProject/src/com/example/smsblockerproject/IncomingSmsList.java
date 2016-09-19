package com.example.smsblockerproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class IncomingSmsList extends Activity
{
	int position1=0;
//	ContactDataBaseFacility cdbf;
	ListView l;
	public static final int nrml_dig=1;
	ArrayList<SMSList> al;
	Cursor c;
	DataBaseFacility dbf;
	MycustomAdapter mc;
	SMSList list;
	String extra=null;
	static final int INCOMINGMESSAGE_ID=4;
	static final int ABUSEID=6;
	
	int MYID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smsinbox);
		
		l=(ListView) findViewById(R.id.inbox);
		al=new ArrayList<SMSList>();
		//cdbf=new ContactDataBaseFacility(this);
		Intent i=getIntent();
		Bundle b=i.getExtras();
		MYID=b.getInt("ID");
		dbf=new DataBaseFacility(this);
		dbf.open();
		//cdbf.open();
		if(MYID==INCOMINGMESSAGE_ID)
		{
		c=dbf.traverseAll(INCOMINGMESSAGE_ID);
		}
		if(MYID==ABUSEID)
		{
		c=dbf.traverseAll(ABUSEID);
		}
		
		if(c!=null)
		{
			while(c.moveToNext())
			{
			if(MYID==INCOMINGMESSAGE_ID)
			{
			list=new SMSList();
			list.setContact_No(c.getString(1));
			list.setMessage(c.getString(2));
			list.setDate(c.getString(3));
			al.add(list);
			}
			if(MYID==ABUSEID)
			{
			list=new SMSList();
			list.setMessage(c.getString(1));
			al.add(list);
			}
			//list.setPic(c.getInt(3));
			
			}
		}
		mc=new MycustomAdapter();
		l.setAdapter(mc);
	
	l.setOnItemClickListener(new OnItemClickListener() {

	
		public void onItemClick(AdapterView<?> l, View v, int pos,
				long pos1) {
			position1=pos;
showDialog(nrml_dig);
			// TODO Auto-generated method stub
			
		}
	});
	
	
	}
	@Override
	
	protected Dialog onCreateDialog(int id) {
		Dialog d=null;
		//final int pos=Integer.parseInt(b.getString("pos"));
		switch(id)
		{
			case nrml_dig:
			{
				AlertDialog.Builder alb=new AlertDialog.Builder(this);
				alb.setTitle("Select Operation");
				alb.setNegativeButton("Block Number", new OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						//Toast.makeText(getApplicationContext(), "Removed",0).show();
						int myid=position1+1;
						SMSList contact=(SMSList)al.get(position1);
						String number=contact.getContact_No();
						String name=contact.getMessage();
						dbf.open();
						dbf.insert(name,number,extra,INCOMINGMESSAGE_ID);
						//cdbf.remove(myid,name,number);
						mc.notifyDataSetChanged();
					}
				});
				alb.setPositiveButton("Cancel", new OnClickListener() {
					
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					
				}
				});
alb.setNeutralButton("Update", new OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						//Toast.makeText(getApplicationContext(), "Updated",0).show();
						int myid=position1+1;
						//Intent i=new Intent(getApplicationContext(),UserForm.class);
						//i.putExtra("id",myid);
						//startActivity(i);
						//SMSList contact=(SMSList)al.get(position1);
						//String number=contact.getContact_No();
						//String name=contact.getMessage();
						
						//dbf.remove(myid,name,number);
						//mc.notifyDataSetChanged();
					}
				});
				d=alb.create();
				break;
				
			}
			
		}
		// TODO Auto-generated method stub
		return d;
	}



private class MycustomAdapter extends BaseAdapter
{

	public int getCount() {
		
		// TODO Auto-generated method stub
		return al.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return al.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View arg1, ViewGroup arg2) {
		RelativeLayout r=(RelativeLayout) getLayoutInflater().inflate(R.layout.smslist, null);
		//ImageView img=(ImageView) r.findViewById(R.id.imageView1);
//		TextView text1=(TextView) r.findViewById(R.id.text1);
//		TextView text2=(TextView) r.findViewById(R.id.text2);
//		TextView text3=(TextView) r.findViewById(R.id.text3);
//		
		if(MYID==INCOMINGMESSAGE_ID)
		{
			TextView text1=(TextView) r.findViewById(R.id.text1);
			TextView text2=(TextView) r.findViewById(R.id.text2);
			TextView text3=(TextView) r.findViewById(R.id.text3);
		text2.setText(al.get(position).getMessage());
		text1.setText(al.get(position).getContact_No());
		text3.setText(al.get(position).getDate());
		}
		if(MYID==ABUSEID)
		{
			
			TextView text1=(TextView) r.findViewById(R.id.text1);
			
			text1.setText(al.get(position).getMessage());
				
		}
		//Bitmap bm=BitmapFactory.decodeResource(getResources(), al.get(position).getPic());
		//img.setImageBitmap(bm);
		
		//Bitmap bmImg = BitmapFactory.decodeFile(al.get(position).getPic());
		//img.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(al.get(position).getPic(),"drawable", getPackageName())));
		//img.setImageResource(al.get(position).getPic());
		
		return r;
	}
	
}


}