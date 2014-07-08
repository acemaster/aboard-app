package com.vivekapp.android.aboard;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.internal.widget.IcsToast;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListDetails extends Activity {
	String name,telno,clat,clongt,flat,flongt,distance,useremail,ulat,ulongt;
	double clatt,clongtt,flatt,flongtt,dist,ulatt,ulongtt;
	TextView n,t;
	Button call,map,callit,msgit,yes,no;
	SharedPreferences pref;
	 List<NameValuePair> params;
	
    Dialog dlg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_details);
		Bundle b = getIntent().getExtras();
		pref = getSharedPreferences("AppPref", MODE_PRIVATE);
		ulat=pref.getString("ulat", "");
		ulongt=pref.getString("ulongt", "");
		ulatt=Double.parseDouble(ulat);
		ulongtt=Double.parseDouble(ulongt);
		name=b.getString("name");
		telno=b.getString("telno");
		clat=b.getString("clat");
		clongt=b.getString("clongt");
		flat=b.getString("flat");
		flongt=b.getString("flongt");
		clatt=Double.parseDouble(clat);
		clongtt=Double.parseDouble(clongt);
		flatt=Double.parseDouble(flat);
		flongtt=Double.parseDouble(flongt);
		dist=caldistance(clatt,clongtt,flatt,flongtt);
		distance=String.valueOf(dist/1000);
		n=(TextView)findViewById(R.id.namelisttxt);
		n.setText(name);
		t=(TextView)findViewById(R.id.telnolisttxt);
		t.setText(telno);
		/*d=(TextView)findViewById(R.id.Distance);*/
	
		call=(Button)findViewById(R.id.callnow);
		call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 dlg = new Dialog(ListDetails.this);
	                dlg.setContentView(R.layout.getride_frag);
	                dlg.setTitle("Get A Ride");
	                callit = (Button)dlg.findViewById(R.id.callit);
	                callit.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							Intent callIntent = new Intent(Intent.ACTION_CALL);
						    callIntent.setData(Uri.parse("tel:"+telno));
						    startActivity(callIntent);
							
						}
					});
	                msgit=(Button)dlg.findViewById(R.id.msgit);
	                msgit.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							 try {
								 Toast.makeText(getBaseContext(),"Sending an sms....",Toast.LENGTH_LONG).show();
								 SmsManager.getDefault().sendTextMessage(telno, null, "Can you offer me a ride?\nFrom "+name+"\n\nSent From Aboard App", null, null);
								 dlg.dismiss();
								 } catch (Exception e) {
								
								 }
						}
					});
				  dlg.show();
				
				
			}
		});
		
		map=(Button)findViewById(R.id.mapit);
		map.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
   	         b.putDouble("clat", ulatt);
   	         b.putDouble("clongt", ulongtt);
   	         b.putDouble("flat", clatt);
   	         b.putDouble("flongt", clongtt);
   	
				
				Intent mapintent=new Intent(ListDetails.this,MapActivity.class);
				mapintent.putExtras(b);
				startActivity(mapintent);
				
				
			}
		});
		useremail=pref.getString("email", "");
		yes=(Button)findViewById(R.id.yes);
		no=(Button)findViewById(R.id.no);
		yes.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				 params = new ArrayList<NameValuePair>();
                 params.add(new BasicNameValuePair("email", useremail));
                 ServerRequest sr = new ServerRequest();
                 JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com/updateno",params);
                 if(json != null){
                     try{
                         String jsonstr = json.getString("response");
                         if(json.getBoolean("res")){
                         
                         IcsToast.makeText(getBaseContext(),"Thank You For Your Response",Toast.LENGTH_SHORT).show();
                         finish();
                         }else {
                             Toast.makeText(getBaseContext(),jsonstr,Toast.LENGTH_SHORT).show();
                         }
                     }catch (JSONException e) {
                         e.printStackTrace();
                     }
                 }
			}
		});
		no.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IcsToast.makeText(getBaseContext(),"Thank you For Your Response",Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_details, menu);
		return true;
	}
	public double caldistance(double c1,double c2,double f1,double f2)
	{
		
		double earthRadius = 3958.75;
	    double dLat = Math.toRadians(c1-f1);
	    double dLng = Math.toRadians(c2-f2);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	               Math.cos(Math.toRadians(c1)) * Math.cos(Math.toRadians(f1)) *
	               Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double dist = earthRadius * c;

	    int meterConversion = 1609;

	    return (double) (dist * meterConversion);
		
		
	}

}
