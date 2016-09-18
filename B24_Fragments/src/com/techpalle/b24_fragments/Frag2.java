package com.techpalle.b24_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Frag2 extends Fragment{
	TextView tv;
	Button b;
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		//take data
		
		//take xml and views
		View v = inflater.inflate(R.layout.frag2, null);
		tv = (TextView) v.findViewById(R.id.textView1);
		//fill data
		tv.setText("Home");
		//return xml
		return v;
	}
}
