package com.example.smsblockerproject;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class SelectPicture extends Activity {

	private static final int RESULT_LOAD_IMAGE = 1;
	protected static final int SD_REQUEST = 2;
	String mySelectedImage="";
	private int[] gal={R.drawable.ic_launcher,R.drawable.images,R.drawable.aish,R.drawable.aish1,R.drawable.aysha,R.drawable.aysha1,R.drawable.akshay,R.drawable.amir,R.drawable.amir1,R.drawable.and1,R.drawable.and2,R.drawable.and3,R.drawable.and5,R.drawable.contact7,R.drawable.john,R.drawable.katrina,R.drawable.madhuri,R.drawable.priyanka,R.drawable.priyanka1,R.drawable.priyanka2,R.drawable.priyanka3,R.drawable.sharukh,R.drawable.vidya};
	Gallery g;
	ImageView img;
	Button myButton,open_Gallery;
	int passImagePath=2;
	int My_Id;
	static final int MSG_ID=1;
	static final int CALL_ID=2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.galleryview);
	    
	    
	    img=(ImageView) findViewById(R.id.imageView1);
	    g=(Gallery) findViewById(R.id.gallery1);
	    myButton=(Button) findViewById(R.id.button1);
	    open_Gallery=(Button) findViewById(R.id.button2);
	    Intent i=getIntent();
	    Bundle b=i.getExtras();
	    My_Id=b.getInt("ID");
	    g.setAdapter(new MyAdapter());
	    
	    myButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//b.putInt("Image",passImagePath);
				Intent i=new Intent();
			    int a=img.getId();
				i.putExtra("Image",passImagePath);
				i.putExtra("MyGalleryImage",mySelectedImage);
				setResult(RESULT_OK, i);
				finish();
				
			}
		});
	    
open_Gallery.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//b.putInt("Image",passImagePath);
				Intent sdintent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				//sdintent.setType("image/*");
				startActivityForResult(sdintent, SD_REQUEST);
				
			}
		});

	    
	    
	    g.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				img.setImageResource(gal[position]);
				passImagePath=gal[position];
				
				// TODO Auto-generated method stub
				
			}
		});
	    // TODO Auto-generated method stub
	}

	private class MyAdapter extends BaseAdapter
	{

		public int getCount() {
			// TODO Auto-generated method stub
			return gal.length;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return gal[position];
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View v, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ImageView iv=new ImageView(getApplicationContext());
			iv.setLayoutParams(new Gallery.LayoutParams(150,100));
			iv.setImageResource(gal[position]);
		
			return iv;
		}
		
	
	}	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == SD_REQUEST && resultCode==RESULT_OK && data!=null) {
        	Uri selectedImage = data.getData();
        	String[] filePathColumn = { MediaStore.Images.Media.DATA };

        	Cursor cursor = getContentResolver().query(selectedImage,
        	                filePathColumn, null, null, null);
        	cursor.moveToFirst();

        	int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        	String filePath = cursor.getString(columnIndex);
           
        	cursor.close();

        	img.setImageBitmap(BitmapFactory.decodeFile(filePath));
        	//Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);

        	//img.setImageBitmap(yourSelectedImage);
        	//ByteArrayOutputStream stream = new ByteArrayOutputStream();
        	//yourSelectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        	//byte[] byteArray = stream.toByteArray();
        	mySelectedImage=filePath;
        	
        	}
            
         
        }
     
     
    }

