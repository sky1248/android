package com.example.smsblockerproject;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsBlockerService extends BroadcastReceiver {
DataBaseFacility dbf;
String MsgContent=null;
String time,date;
static final int MSG_ID=1;
static final int ABUSEID=6;
static final int INCOMINGMESSAGEID=4;
	@Override
	public void onReceive(Context context, Intent intent) {
		dbf=new DataBaseFacility(context);
		dbf.open();
		SmsMessage [] messages = null;
        String strMessage = "";
        
		Cursor c=dbf.traverseAll(MSG_ID);
		Cursor c1=dbf.traverseAll(ABUSEID);
		if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
		{
			Toast.makeText(context,"Message Coming...", 0).show();
			//abortBroadcast();
			Bundle pudsBundle = intent.getExtras();
            if (pudsBundle != null)
            {
                Object [] pdus = (Object[]) pudsBundle.get("pdus");
                messages = new SmsMessage[pdus.length];

                for (int i = 0; i < messages.length; i++)
                {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                    strMessage += " : ";
                    strMessage += messages[i].getMessageBody();
                    
                }
               
                Calendar cal=Calendar.getInstance();
                time=cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE);
                 date=String.valueOf(cal.get(Calendar.DATE)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR));
                  } 
            
            
                //SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);    
           // Log.i(TAG,  messages.getMessageBody());
                //if(messages.getMessageBody().contains("Hi")) {
                  //  abortBroadcast();
			//Toast.makeText(context, "Message sender is "+messages.getOriginatingAddress(),0).show();
			//Toast.makeText(context,"SMS Blocked...", 0).show();
			
            
            
            if(c!=null)
			{
				while(c.moveToNext())
				{
					if(messages[0].getOriginatingAddress().equals(c.getString(2)))
					{
						Toast.makeText(context,"Message Ariving from Blocked Listed Numbers",0).show();
						Toast.makeText(context,"The Message is sent by "+messages[0].getOriginatingAddress()+"and it is Blocked",0).show();
						dbf.insert(messages[0].getOriginatingAddress(), strMessage," date ="+date+"   Time="+time, INCOMINGMESSAGEID);
				        abortBroadcast();
					}
				}
					
				}
            
            if(c1!=null)
			{
				while(c1.moveToNext())
				{
					if(strMessage.toLowerCase().contains(c1.getString(1).toLowerCase()))
					{
						Toast.makeText(context," it is Blocked Successfully Content is"+strMessage,0).show();
						Toast.makeText(context,"There was Abuses/Blocked Word in the Message and The Message was sent by"+messages[0].getOriginatingAddress(),0).show();
						Toast.makeText(context,"Time="+time+"Date="+date,0).show();
						
						dbf.insert(messages[0].getOriginatingAddress(), strMessage," date ="+date+"   Time="+time, INCOMINGMESSAGEID);
						abortBroadcast();
					}
				}
			}
				//Toast.makeText(context,"The Message is sent by"+messages.getOriginatingAddress()+"hello"+c.getString(1),0).show();
            }
		}
	}

		
	    // TODO Auto-generated method stub
	


