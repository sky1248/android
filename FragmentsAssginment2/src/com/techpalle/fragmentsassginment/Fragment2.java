package com.techpalle.fragmentsassginment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment{
	TextView tv;
	
	public Fragment2() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		Intent in = getActivity().getIntent();
		String topic = in.getStringExtra("topic");
		//take frag2.xml
		View v = inflater.inflate(R.layout.frag2, null);
		//take views from frag2.xml
		tv = (TextView) v.findViewById(R.id.textView1);
		//fill data onto views
		
		tv.setText("Bascis");
		tv.setText(topic);
		//return frag2.xml
		return v;
	}
	
	/*
	 * frag1 will call this -> to pass data
	 * This will happen only in landscape mode
	 */
	public void giveData(String str){
		tv.setText(str);
	}
}