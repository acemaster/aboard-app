package com.vivekapp.android.aboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.widget.IcsToast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Getaride extends SherlockFragment {
	
	Button getaride;
	SharedPreferences pref;
	List<NameValuePair> params;
	String email,ulat,ulongt;
	TextView noofrides;

	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {

	        View view = inflater.inflate(R.layout.activity_getaride, container, false);
	        return view;
	    }
	 @Override
	    public void onActivityCreated (Bundle savedInstanceState)
	    {
		 super.onActivityCreated(savedInstanceState);
	        super.onCreate(savedInstanceState);
	        pref = getActivity().getBaseContext().getSharedPreferences("AppPref", Context.MODE_PRIVATE);
			ulat=pref.getString("ulat", "");
			ulongt=pref.getString("ulongt", "");
			
		    getaride=(Button)getActivity().findViewById(R.id.getaride);
		    getaride.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					if(ulat!=null || ulongt!=null){
					 Intent profactivity = new Intent(getActivity(),Listride.class);
                     startActivity(profactivity);
					}
					else{
						Toast.makeText(getActivity(), "Please update location", Toast.LENGTH_SHORT).show();
					}
					
				}
			});
		    noofrides=(TextView)getActivity().findViewById(R.id.nooftxt);
		    pref = getActivity().getBaseContext().getSharedPreferences("AppPref",Context.MODE_PRIVATE);
		    email=pref.getString("email", "");
		    params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email",email));
            
            ServerRequest sr = new ServerRequest();
        //    JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/chgpass",params);
            JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com/noofrides",params);
            if(json != null){
                try{
                    String jsonstr = json.getString("noofrides");
                    if(json.getBoolean("res")){
                    	noofrides.setText("No of rides: "+jsonstr);
   
                    }else {
                       
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
		    	
		    }
		    
	    }


