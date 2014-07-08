package com.vivekapp.android.aboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Listride extends Activity  {
    
    SharedPreferences pref;
    String[] nameArray,idArray,clatArray,clongtArray;
    String flat,flongt;
   

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listride);
		 ListView l=(ListView)findViewById(R.id.list);
		 pref = this.getBaseContext().getSharedPreferences("AppPref",Context.MODE_PRIVATE);
	     String name = pref.getString("name", "");
		
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		ServerRequest sr = new ServerRequest();
    	 JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com//getaride",params);
    	 List<String> names = new ArrayList<String>();
    	 List<String> telno = new ArrayList<String>();
    	 List<String> clat = new ArrayList<String>();
    	 List<String> clongt = new ArrayList<String>();
    	 
    	 JSONArray js=new JSONArray();
    	 
    	

    	 try {
    		  
			js=json.getJSONArray("rides");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 
    	 
		 
		
    	 for(int i = 0 ; i < js.length(); i++){
    		 JSONObject j=new JSONObject();
    		    try {
					 j=js.getJSONObject(i);
					 String name2=j.getString("name");
					 names.add(name2);
		    		 String id=j.getString("telno");
		    		 telno.add(id);
		    		 String clatt=j.getString("clat");
		    		 clat.add(clatt);
		    		 String clongtt=j.getString("clongt");
		    		 clongt.add(clongtt);
		    		 flat=j.getString("flat");
		    		 flongt=j.getString("flongt");
		    		 
		    		 
		    		 
		    		 
		    		 
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		    
    		
    		    
    		}
    	 nameArray = names.toArray(new String[0]);
    	  idArray = telno.toArray(new String[0]);
    	  clatArray =  clat.toArray(new String[0]);
    	  clongtArray = clongt.toArray(new String[0]);
		 
    	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_list_item_1, nameArray);
    	 l.setAdapter(adapter);
    	 l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		 
    	     public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
    	                             long id) {
    	              
    	      
    	         // We know the View is a TextView so we can cast it
    	         TextView t1 = (TextView) view;
    	         Intent intent = new Intent(Listride.this, ListDetails.class);
    	         Bundle b = new Bundle();
    	         b.putString("name",t1.getText().toString());
    	         b.putString("telno", idArray[position]);
    	         b.putString("clat",clatArray[position]);
    	         b.putString("clongt",clongtArray[position]);
    	         b.putString("flat",flat);
    	         b.putString("flongt",flongt);
    	         intent.putExtras(b); //Put your id to your next Intent
    	         startActivity(intent);
    	         finish();
    	     }});
    
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listride, menu);
		return true;
	}
	
	public void caldistance(double clat,double clongt,double flat,double flongt)
	{
		
	}

}
