package com.example.smsblockerproject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.speech.RecognitionService;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.android.internal.telephony.ITelephony;

public class CallControllerReceiver extends BroadcastReceiver{

	int CALLID=2;
	Cursor cursor=null;
	DataBaseFacility dbf;
	TelephonyManager telephonyManager;
	 ITelephony telephonyService;
	@Override
	public void onReceive(Context context, Intent arg1) {
		
		
		dbf=new DataBaseFacility(context);
		dbf.open();
		
		cursor=dbf.traverseNumber(CALLID);
		telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		 //Java Reflections
		 Class c = null;
		 try {
			 
		 c = Class.forName(telephonyManager.getClass().getName());
		 } catch (ClassNotFoundException e) {
			 Toast.makeText(context,"Class Not found", 0).show();
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 Method m = null;
		 try {
			
		 m = c.getDeclaredMethod("getITelephony");
		 } catch (SecurityException e) {
			 Toast.makeText(context,"Security exception", 0).show();
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 } catch (NoSuchMethodException e) {
			 Toast.makeText(context,"Method not found", 0).show();
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		 m.setAccessible(true);
		 try {
		 telephonyService = (ITelephony)m.invoke(telephonyManager);
		
		 } catch (IllegalArgumentException e) {
		 // TODO Auto-generated catch block
			 Toast.makeText(context,"illegal arguments", 0).show();
		 e.printStackTrace();
		 } catch (IllegalAccessException e) {
		 // TODO Auto-generated catch block
			 Toast.makeText(context,"illegal access", 0).show();
		 e.printStackTrace();
		 } catch (InvocationTargetException e) {
			 Toast.makeText(context,"invocation Target", 0).show();
		 // TODO Auto-generated catch block
		 e.printStackTrace();}
		// TODO Auto-generated method stub
		 PhoneCallStateListener customPhoneListener = new PhoneCallStateListener(context);
		    telephonyManager.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
		
		 
	}		 
	public class PhoneCallStateListener extends PhoneStateListener {    

		private Context context;
		public PhoneCallStateListener(Context context){
		    this.context = context;
		   
		}


		@Override
		public void onCallStateChanged(int state, String incomingNumber) {  
		    SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);
   
		    switch (state) {   
		    
		    case TelephonyManager.CALL_STATE_OFFHOOK:
		    {

		    
		    }
		    
		    
		    case TelephonyManager.CALL_STATE_RINGING:
		        	Toast.makeText(context,"Ringing", 0).show();

		        	try {
						
		                   // AudioManager am=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		                    //am.setStreamMute(LISTEN_CALL_STATE, true);
							//telephonyService.silenceRinger();
		                   if(cursor!=null)
		                   {
		                	   while(cursor.moveToNext())
		                	   {
		                		   if(cursor.getString(0).equals(incomingNumber))
		                		   {
		                			   telephonyService.endCall();  
		                		   }
		                	   }
		                   }
		                    
						} catch (RemoteException e) {
							Toast.makeText(context,"Remote Exception", 0).show();

							// TODO Auto-generated catch block
							Toast.makeText(context,"Some exception is there",0).show();
							e.printStackTrace();
						}

		        	/*Toast.makeText(context,"its ringing.."+incomingNumber, 0).show();
		              //String block_number = prefs.getString("block_number", null);
		            //String block_number="9988007441";
		        	//AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE); 
		            //Turn ON the mute
		            //audioManager.setStreamMute(AudioManager.STREAM_RING, true);     
				try {
					
                    AudioManager am=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    am.setStreamMute(LISTEN_CALL_STATE, true);
					//telephonyService.silenceRinger();
                   if(cursor!=null)
                   {
                	   while(cursor.moveToNext())
                	   {
                		   if(cursor.getString(0).equals(incomingNumber))
                		   {
                			   telephonyService.endCall();  
                		   }
                	   }
                   }
                    
				} catch (RemoteException e) {
					Toast.makeText(context,"Remote Exception", 0).show();

					// TODO Auto-generated catch block
					Toast.makeText(context,"Some exception is there",0).show();
					e.printStackTrace();
				}*/
		            
		}
		    }
	}
}

