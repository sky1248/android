package com.example.smsblockerproject;

import java.io.ByteArrayOutputStream;

import com.example.smsblockerproject.R.id;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CallBlockForm extends Activity  {
Button button_Block,button_BlockList,button_Update;
ImageView img_CallProfile;
String Old_Number=null;
EditText edit_Name,edit_Number;
String Name,Number,PicId="R.Drawable.ic_launcher";
DataBaseFacility dbf;
int Pic_Id=R.drawable.ic_launcher;;
static final int CALLID =2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.callblockform);
	    img_CallProfile=(ImageView) findViewById(R.id.img);
	    edit_Number=(EditText) findViewById(R.id.edit1);
	    edit_Name=(EditText) findViewById(R.id.edit2);
	    button_Block=(Button) findViewById(R.id.block);
	    button_BlockList=(Button) findViewById(R.id.blackList);
	    button_Update=(Button) findViewById(R.id.update);
	    int id;
	    Intent i=getIntent();
		Bundle b=i.getExtras();
		if(b!=null)
		{ 
			Old_Number=b.getString("number");
			id=b.getInt("id");
			edit_Number.setText(b.getString("number"));
			edit_Name.setText(b.getString("name"));
			Pic_Id=b.getInt("Pic_Id");
			img_CallProfile.setImageResource(Pic_Id);
        	
		}
		
	    dbf=new DataBaseFacility(this);
	    dbf.open();
	    // TODO Auto-generated method stub
	}
	
	public void myClick(View v) {
		// TODO Auto-generated method stub
	switch(v.getId())
	{
		case R.id.block:
		{
			Name=edit_Name.getText().toString();
			Number=edit_Number.getText().toString();
			
			if(Number.equals(""))
			{
				Toast.makeText(getApplicationContext(),"Plz Enter Proper Number to Block",0).show();
			}
			else
			{
				dbf.insert(Name,Number,PicId,CALLID);
			}
			break;
			
		}
		
		case R.id.blackList:
		{
			Intent i=new Intent(getApplication(),BlockList.class);
			i.putExtra("ID", CALLID);
			startActivity(i);
			break;
			
		}
		case R.id.update:
		{
			String my_number=edit_Number.getText().toString();
			String message=edit_Name.getText().toString();
			
			
			Pic_Id=img_CallProfile.getId();
			//String pic=PicId;
			// TODO Auto-generated method stub
			if(my_number.equals(""))
			{
				Toast.makeText(getApplicationContext(),"Plz Enter some number",0).show();
			}
			else
			{
				dbf.update(message,my_number,Old_Number,String.valueOf(Pic_Id),CALLID);
			}
			
			break;	
		}
		case R.id.img:
		{
			Intent i=new Intent(getApplicationContext(),SelectPicture.class);
			Toast.makeText(getApplicationContext(),"Image ..",0).show();;
			i.putExtra("ID",CALLID);
			startActivityForResult(i,1);
			break;
		}
	}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle b=data.getExtras();
		int pic_Id=b.getInt("Image");
		String MyImagePath=b.getString("myGalleryImage");
		if(pic_Id==2)
		{
			PicId=MyImagePath;
			Bitmap 	yourSelectedImage = BitmapFactory.decodeFile(MyImagePath);

			img_CallProfile.setImageBitmap(yourSelectedImage);
			
		}
		else
		{
			PicId=String.valueOf(pic_Id);
			img_CallProfile.setImageResource(pic_Id);
		}
		// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
		}

}


