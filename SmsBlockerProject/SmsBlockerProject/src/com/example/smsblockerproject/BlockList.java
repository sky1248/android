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



public class BlockList extends Activity
{
	int position1=0;
	DataBaseFacility dbf;
	ListView l;
	public static final int nrml_dig=1;
	ArrayList<list_Contact> al;
	Cursor c;
	MycustomAdapter mc;
	list_Contact list;
	static final int MSG_ID=1;
	static final int CALL_ID=2;
	static final int CONTACT_ID=3;
	int MyIntentId=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blocklist1);
		
		l=(ListView) findViewById(R.id.listView1);
		al=new ArrayList<list_Contact>();
		dbf=new DataBaseFacility(this);
		dbf.open();
		Intent i=getIntent();
		Bundle b=i.getExtras();
	    MyIntentId=b.getInt("ID");
		if(MyIntentId==MSG_ID)
		{
		c=dbf.traverseAll(MSG_ID);
		}
		if(MyIntentId==CALL_ID)
		{
		c=dbf.traverseAll(CALL_ID);
		}
		if(MyIntentId==CONTACT_ID)
		{
		c=dbf.traverseAll(CONTACT_ID);
		}
		
		
		if(c!=null)
		{
			while(c.moveToNext())
			{
			list=new list_Contact();
			list.setMessage(c.getString(1));
			list.setContact_No(c.getString(2));
			list.setPic(c.getInt(3));
			
			al.add(list);
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
				alb.setNegativeButton("Remove From List", new OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
						Toast.makeText(getApplicationContext(), "Removed",0).show();
						int myid=position1+1;
						list_Contact contact=(list_Contact)al.get(position1);
						String number=contact.getContact_No();
						String name=contact.getMessage();
						if(MyIntentId==MSG_ID)
						{
							dbf.remove(number,MSG_ID);
						}
						if(MyIntentId==CALL_ID)
						{
							dbf.remove(number,CALL_ID);
						}
					
						
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
						Intent i=null;
						int myid=position1+1;
						if(MyIntentId==MSG_ID)
						{
							i=new Intent(getApplicationContext(),UserForm.class);
						}
						if(MyIntentId==CALL_ID)
						{
							i=new Intent(getApplicationContext(),CallBlockForm.class);
						}
						i.putExtra("id",myid);
						list_Contact contact=(list_Contact)al.get(position1);
						String number=contact.getContact_No();
						String name=contact.getMessage();
						String Pic_Id=String.valueOf(contact.getPic());
						i.putExtra("name", name);
						i.putExtra("number",number);
						i.putExtra("Pic_Id",al.get(position1).getPic());
						
						
						startActivity(i);
						//list_Contact contact=(list_Contact)al.get(position1);
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
		RelativeLayout r=(RelativeLayout) getLayoutInflater().inflate(R.layout.list1, null);
		ImageView img=(ImageView) r.findViewById(R.id.imageView1);
		TextView text1=(TextView) r.findViewById(R.id.text1);
		TextView text2=(TextView) r.findViewById(R.id.text2);
		text1.setText(al.get(position).getMessage());
		text2.setText(al.get(position).getContact_No());
		//Bitmap bm=BitmapFactory.decodeResource(getResources(), al.get(position).getPic());
	    //img.setImageBitmap(bm);
		
		//Bitmap bmImg = BitmapFactory.decodeFile(al.get(position).getPic());
		//img.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(al.get(position).getPic(),"drawable", getPackageName())));
		img.setImageResource(al.get(position).getPic());
		
		
		return r;
	}
	
}


}