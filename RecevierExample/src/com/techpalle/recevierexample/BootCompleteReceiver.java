package com.techpalle.recevierexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
	    //this method has to be called once booting is over
		Toast.makeText(context, "VIRUS!!!", 0).show();
		Log.d("VIRUS", "VIRUS");
		Intent in = new Intent(context, MainActivity.class);
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(in);
	}
}
