package com.example.smsblockerproject;

import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


//public class CallBlockReceiver extends BroadcastReceiver
//{
//	Context mycon;
//	@Override
//public void onReceive(Context context, Intent intent)
//{
//	 mycon=context;
//  /*  TelephonyManager tMgr = (TelephonyManager) context
//.getSystemService(Context.TELEPHONY_SERVICE);
//       String mPhoneNumber = tMgr.getLine1Number();
//       Toast.makeText(context,"The Caller is "+mPhoneNumber,0).show();
//*/
//	 
//	 TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//	   // CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();
//
//	  //  telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
//
//
//	    Bundle bundle = intent.getExtras();
//	    String phoneNr= bundle.getString("incoming_number");
//	  //  Log.v(TAG, "phoneNr: "+phoneNr);
//
//}
//}





//public class CallBlockReceiver extends BroadcastReceiver {
//
//private static final String TAG = "CustomBroadcastReceiver";
//
//@Override
//public void onReceive(Context context, Intent intent) {
//    Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
//    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//    CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();
//
//    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
//    
//
//    Bundle bundle = intent.getExtras();
//    String phoneNr= bundle.getString("incoming_number");
//    Log.v(TAG, "phoneNr: "+phoneNr);
//    Toast.makeText(context,"Incoming number="+phoneNr,0).show();
//    TelephonyManager telephonyManager =((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
//    String operatorName = telephonyManager.getNetworkOperatorName();
//   // similarly the sim operator can be retrieved by using:
//Toast.makeText(context, "Network used="+operatorName, 0).show();
//    String SimName = telephonyManager.getSimOperatorName();
//    Toast.makeText(context, "Sim used="+SimName, 0).show();
//    
//    try
//    {
//    Class c = Class.forName(telephony.getClass().getName());    
//    Method m = c.getDeclaredMethod("getITelephony");
//     m.setAccessible(true);
//     m.invoke(telephony);
//     telephony = (TelephonyManager) m.invoke(telephony);          
//  
//    }
//    catch(Exception e)
//    {}
//    
////    
//    
//    AudioManager amanager=(AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//    amanager.setStreamVolume(AudioManager.STREAM_RING, 7, AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
//    if(phoneNr.contains("+919988007441"))
//    {
//    	abortBroadcast();
//    }
//
//}
//public class CustomPhoneStateListener extends PhoneStateListener {
//
//private static final String TAG = "CustomPhoneStateListener";
//public void onCallStateChange(int state, String incomingNumber){
//
//    Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
//    Log.v(TAG, incomingNumber);
//
//    switch(state){
//        case TelephonyManager.CALL_STATE_RINGING:
//            Log.d(TAG, "RINGING");
//            break;
//    }
//   
//}
//}
//}


class CallBlockReceiver extends BroadcastReceiver
{public void onReceive(Context context, Intent intent) { 
    TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    PhoneCallStateListener customPhoneListener = new PhoneCallStateListener(context);
    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);



}}
	