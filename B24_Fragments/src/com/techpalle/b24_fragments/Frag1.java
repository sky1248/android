package com.techpalle.b24_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Frag1 extends Fragment{
	ListView lv;
	String[] items = {"Home", "About us", "Trainings", "Enquiry"};
	ArrayAdapter<String> aa;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		//take data (data is already in array)
		//load xml file of your fragment and take views
		View v = inflater.inflate(R.layout.frag1, null);
		//file data
		lv = (ListView) v.findViewById(R.id.listView1);
		aa = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, 
				items);
		lv.setAdapter(aa);
		//return xml file
		return v;
		
	}
}