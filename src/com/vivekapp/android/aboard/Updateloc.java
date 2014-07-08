package com.vivekapp.android.aboard;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.widget.IcsToast;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.Toast;




public class Updateloc extends SherlockFragment {
	
	    SharedPreferences pref;
	    Button updateloc,map,curloc;
	    AutoCompleteTextView currentloc;
	    AutoCompleteTextView finalloc;
        String name,telno,clat,clongt,flat,flongt,token;


	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {

	        View view = inflater.inflate(R.layout.updateloc, container, false);
	        return view;
	    }
	    @Override
	    public void onActivityCreated (Bundle savedInstanceState)
	    {
	    	super.onActivityCreated(savedInstanceState);
	        super.onCreate(savedInstanceState);
	        String[] countries = getResources().
	        		   getStringArray(R.array.list_of_countries);
	        		   ArrayAdapter adapter = new ArrayAdapter(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,countries);
	        		   pref = getActivity().getBaseContext().getSharedPreferences("AppPref",Context.MODE_PRIVATE);
	        	        token = pref.getString("token", "");
 
	        		   currentloc = (AutoCompleteTextView) getActivity().findViewById(R.id.autoCompleteTextView1);
	        		   finalloc = (AutoCompleteTextView) getActivity().findViewById(R.id.AutoCompleteTextView2);

	        		   currentloc.setAdapter(adapter);
	        		   finalloc.setAdapter(adapter);
                       updateloc=(Button)getActivity().findViewById(R.id.updateloc);   
                       updateloc.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View view) {
							String cname=currentloc.getText().toString();
							String fname=finalloc.getText().toString();
							getlocation(cname,fname,view);
							ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("token", token));
							ServerRequest sr = new ServerRequest();
					    	 JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com/getbytoken",params);
					    	 if(json != null){
				                 try{
				                     String jsonstr = json.getString("response");
				                     name=json.getString("name");
				                     telno=json.getString("telno");
				                    	
				                     if(json.getBoolean("res")){
				                 
				                     IcsToast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
				                     }else {
				                         Toast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
				                     }
				                 }catch (JSONException e) {
				                     e.printStackTrace();
				                 }
				             }
					    	
					    	 ArrayList<NameValuePair> params2 = new ArrayList<NameValuePair>(); 
					    	 params2.add(new BasicNameValuePair("name", name));
					    	 params2.add(new BasicNameValuePair("telno", telno));
					    	 params2.add(new BasicNameValuePair("clat", clat));
					    	 params2.add(new BasicNameValuePair("clongt", clongt));
					    	 params2.add(new BasicNameValuePair("flat", flat));
					    	 params2.add(new BasicNameValuePair("flongt", flongt));
					    	 ServerRequest sr2 = new ServerRequest();
					    	 JSONObject json2 = sr2.getJSON("http://powerful-tor-5829.herokuapp.com/chgaddress",params2);
					    	 if(json2 != null){
				                 try{
				                     String jsonstr = json2.getString("response");
				                    
				                     SharedPreferences.Editor edit = pref.edit();
				                     edit.putString("ulat", clat);
				                     edit.putString("ulongt",clongt);
				                     edit.commit();
				                     if(json.getBoolean("res")){
				                 
				                     IcsToast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
				                     }else {
				                         Toast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
				                     }
				                 }catch (JSONException e) {
				                     e.printStackTrace();
				                 }
				             }
					    	 
							
							
						}
						
					});
                       map=(Button)getActivity().findViewById(R.id.showonmap);
               		map.setOnClickListener(new View.OnClickListener() {
               			
               			@Override
               			public void onClick(View v) {
               				Bundle b = new Bundle();
                  	         b.putDouble("clat", Double.parseDouble(clat));
                  	         b.putDouble("clongt", Double.parseDouble(clongt));
                  	         b.putDouble("flat", Double.parseDouble(flat));
                  	         b.putDouble("flongt", Double.parseDouble(flongt));
                  	
               				
               				Intent mapintent=new Intent(getActivity(),MapActivity.class);
               				mapintent.putExtras(b);
               				startActivity(mapintent);
               				
               				
               			}
               		});
              curloc=(Button)getActivity().findViewById(R.id.getcurloc);
              curloc.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					
				}
			});
	    }
	    public void getlocation(String cname, String fname, View view)
	    {
	    	
	    	 ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
	    	 params.add(new BasicNameValuePair("name", cname));
	    	 ServerRequest sr = new ServerRequest();
	    	 JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com/searchitem",params);
             if(json != null){
                 try{
                     String jsonstr = json.getString("response");
                     clat=json.getString("lat");
                     clongt=json.getString("longt");
                    	
                     if(json.getBoolean("res")){
                 
                     IcsToast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
                     }else {
                         Toast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
                     }
                 }catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
	    
	    
	   ArrayList<NameValuePair> paramsv = new ArrayList<NameValuePair>();
   	 paramsv.add(new BasicNameValuePair("name", fname));
   	 ServerRequest sr2 = new ServerRequest();
   	 JSONObject json2 = sr2.getJSON("http://powerful-tor-5829.herokuapp.com/searchitem",paramsv);
        if(json != null){
            try{
                String jsonstr = json.getString("response");
                flat=json2.getString("lat");
                flongt=json2.getString("longt");
               	
                if(json2.getBoolean("res")){
            
                IcsToast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(view.getContext(),jsonstr,Toast.LENGTH_SHORT).show();
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
   }

}




