package com.techpalle.fragmentsassginment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//step 4
public class Fragment1 extends Fragment{
	
	private class InnerFragment extends Fragment{
		
	}
	//step 5 : declare all variables
	ArrayList<Questions> al;
	ListView lv;//destination
	MyAdapter ma;//adapter
	
	//step 7 : implement oncreateview
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle savedInstanceState) {
		//fill data into arraylist
		al = new ArrayList<Questions>();
		Questions q1=new Questions();
		q1.setTopic("Basics");
		q1.setPapers("6 papers");
		q1.setQuestions("60 questions");
		al.add(q1);
		Questions q2=new Questions();
		q2.setTopic("Advanced");
		q2.setPapers("2 papers");
		q2.setQuestions("20 questions");
		al.add(q2);
		Questions q3=new Questions();
		q3.setTopic("Queries");
		q3.setPapers("3 papers");
		q3.setQuestions("25 questions");
		al.add(q3);
		Questions q4=new Questions();
		q4.setTopic("Questions");
		q4.setPapers("5 papers");
		q4.setQuestions("50 questions");
		al.add(q4);
		
		//load frag1.xml
		View v = inflater.inflate(R.layout.frag1, null); 
		//take listview from frag1.xml
		lv = (ListView) v.findViewById(R.id.listView1);
		//create object for custom adapter
		ma = new MyAdapter();
		//set adapter to listview
		lv.setAdapter(ma);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, 
					View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String item = al.get(position).getTopic(); 
				
				/*
				 * find out if you are landscape or portrait mode
				 */
				//IF F2 IS AVAIALBLE - IT IS LANDSCAPE 
				//ELSE - IT IS PROTRATE
				FragmentManager fm = getActivity().getSupportFragmentManager();
				Fragment frag2 = fm.findFragmentById(R.id.f2);
				if(frag2 == null){
					//THAT MEANS FRAG2 IS NOT THERE - PORTRAIT
					//now start second activity
					Intent in = new Intent(getActivity(), 
							SecondActivity.class);
					in.putExtra("topic", item);
					startActivity(in);
				}else{
					Fragment2 f2 = (Fragment2) frag2;
					//THAT MEANS FRAG2 IS THERE - LANDSCAPE
					//pass data to frag2
					f2.giveData(item);
				}
			}
		});
		//return frag1.xml
		return v;
	}
	
	//step 6 : create custom adapter
	class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return al.size();
		}
		@Override
		public Object getItem(int position) {
			return al.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, 
				View convertView, ViewGroup parent) {
			//take row.xml
			View v = getActivity().getLayoutInflater().
					inflate(R.layout.row, null);
			//take views from row.xml
			TextView tv1 = (TextView) v.findViewById(R.id.textView1);
			TextView tv2 = (TextView) v.findViewById(R.id.textView2);
			TextView tv3 = (TextView) v.findViewById(R.id.textView3);
			//take data from al (source)
			Questions q = al.get(position);
			String topic = q.getTopic();
			String papers = q.getPapers();
			String questionis = q.getQuestions();
			//fill data onto views
			tv1.setText(topic);
			tv2.setText(papers);
			tv3.setText(questionis);
			//return row.xml
			return v;
		}
		
	}
}
