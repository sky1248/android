package com.example.smsblockerproject;

import java.io.ByteArrayOutputStream;
import java.util.DuplicateFormatFlagsException;
import java.util.HashMap;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserForm extends Activity {
Button block_Number,button_AbusesList,list_BlockNumber,button_Contact,button_Update,button_DisplayContacts,button_IncomingMsg,button_SpecialBlock;
ImageView image;
EditText number,name;
String imagePath="R.Drawable.ic_launcher";
String Old_Number=null;
int id;
String Pic_Id=null;
static final int SMSID=1;
int PicId;
static final int CONTACT_ID=3;
static final int INCOMINGID=4;
static final int ABUSEID=6;
protected static final String contactNumber=null;
DataBaseFacility dbf;
//ContactDataBaseFacility cdbf;
/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.userform);
	block_Number=(Button) findViewById(R.id.button1);
	list_BlockNumber=(Button)findViewById(R.id.button3);
	button_Contact=(Button) findViewById(R.id.button2);
    button_Update=(Button) findViewById(R.id.button4);
    button_DisplayContacts=(Button) findViewById(R.id.button5);
    button_IncomingMsg=(Button) findViewById(R.id.button6);
    button_SpecialBlock=(Button) findViewById(R.id.button7);
    button_AbusesList=(Button) findViewById(R.id.button8);
    image=(ImageView) findViewById(R.id.imageView1);
	number=(EditText) findViewById(R.id.editText1);
	name=(EditText) findViewById(R.id.editText2);
	Intent i=getIntent();
	Bundle b=i.getExtras();
	if(b!=null)
	{
	Old_Number=b.getString("number");
	id=b.getInt("id");
	number.setText(b.getString("number"));
	name.setText(b.getString("name"));
	PicId=b.getInt("Pic_ID");
	
	//Bitmap yourSelectedImage = BitmapFactory.decodeFile(Pic_Id);

	//image.setImageBitmap(Pic_Id);
	image.setImageResource(PicId);
	
	}
	dbf=new DataBaseFacility(this);
	dbf.open();
	
	
	image.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
		Intent i=new Intent(getApplicationContext(),SelectPicture.class);
		i.putExtra("ID",SMSID);
		startActivityForResult(i,1);
		}
	});
	
	block_Number.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			int duplicateEntry=0;
			String my_number=number.getText().toString();
			String message=name.getText().toString();
			String pic=imagePath;
			if(my_number.equals(""))
			{
				Toast.makeText(getApplicationContext(),"Plz Enter some number",0).show();
			}
			else
			{
				
				Cursor c=dbf.traverseAll(SMSID);
				if(c!=null)
				{
					while(c.moveToNext())
					{
						
						if(c.getString(2).equals(my_number))
					
						{
							Toast.makeText(getApplicationContext(), "Number is Already in List",0).show();
							duplicateEntry++;
							break;
						}
					}
				}
			}
			if(duplicateEntry<=0)
			{
				dbf.insert(message,my_number,pic,SMSID);
			}
			// TODO Auto-generated method stub
			
		}
	});
	
	list_BlockNumber.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {
			Intent i=new Intent(getApplicationContext(),BlockList.class);
			i.putExtra("ID",SMSID);
			startActivity(i);
			
			// TODO Auto-generated method stub
			
		}
	});

button_Contact.setOnClickListener(new OnClickListener() {
	
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//Cursor cursor=getContentResolver().query(android.provider.ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
	//String contactName=null;
	//String contactNumber=null;
      dbf.remove(contactNumber,CONTACT_ID);
	
	
	ContentResolver contactResolver = getContentResolver(); 
	Cursor cursor = contactResolver.query(Phone.CONTENT_URI, null,null, null, null);
	Toast.makeText(getApplicationContext(), "Plz Wait.. Loading Numbers into database", 0).show();

	if(cursor.getCount() > 0){
	    cursor.moveToFirst();
	    do {
	    	String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
	       String number = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
	       dbf.insert(name,number,imagePath,CONTACT_ID);
	      
	    }while (cursor.moveToNext()); 
	    Intent i=new Intent(getApplicationContext(),MobileContactList.class);
	    startActivity(i);
	    
	}

	
	
	}
});
button_Update.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		int NDuplicateEntry=0;
		String my_number=number.getText().toString();
		String message=name.getText().toString();
		String pic=imagePath;
		// TODO Auto-generated method stub
		
		
		
		if(my_number.equals(""))
		{
			Toast.makeText(getApplicationContext(),"Plz Enter some number",0).show();
		}
		
		else
		{
			Cursor c=dbf.traverseAll(SMSID);
			if(c!=null)
			{
				while(c.moveToNext())
				{
					
					if(c.getString(2).equals(my_number) && my_number.equals(Old_Number) || !c.getString(2).equals(my_number))
				
					{
						Toast.makeText(getApplicationContext(), "Number is Already in List",0).show();
						NDuplicateEntry++;
						break;
					}
				}
			}
		}
		
		if(NDuplicateEntry>0)
		{
			dbf.update(message,my_number,Old_Number,pic,SMSID);
		}
	}
});

button_IncomingMsg.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	Intent i=new Intent(getApplicationContext(),IncomingSmsList.class);
	i.putExtra("ID",INCOMINGID);
	startActivity(i);
	
	}
});

button_DisplayContacts.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	Intent i=new Intent(getApplicationContext(),MobileContactList.class);
	i.putExtra("ID", CONTACT_ID);
	startActivity(i);
	}
});
	// TODO Auto-generated method stub
button_SpecialBlock.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		Intent i=new Intent(getApplicationContext(),BlockSpecialMessage.class);
		startActivity(i);
	}
});

button_AbusesList.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		Intent i=new Intent(getApplicationContext(),IncomingSmsList.class);
		i.putExtra("ID",ABUSEID);
		startActivity(i);
	}
});	
	}
	@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle b=data.getExtras();
		int MyPicId=b.getInt("Image");
		String MyImagePath=b.getString("myGalleryImage");
		if(MyPicId==2)
		{
			Bitmap 	yourSelectedImage = BitmapFactory.decodeFile(MyImagePath);

			image.setImageBitmap(yourSelectedImage);
			imagePath=MyImagePath;
			
		}
		else
		{
			image.setImageResource(MyPicId);
			imagePath=String.valueOf(MyPicId);
		}
		// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
		}

}
