package com.vivekapp.android.aboard;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class MapActivity extends FragmentActivity {
	 
	GoogleMap googleMap;
	double clat,clongt,flat,flongt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		 try {
	            // Loading map
	            initilizeMap();
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 Bundle b = getIntent().getExtras();
		 clat=b.getDouble("clat");
		 flat=b.getDouble("flat");
		 clongt=b.getDouble("clongt");
		 flongt=b.getDouble("flongt");
		 LatLng Kuwait=new LatLng(clat,clongt);
		 googleMap.animateCamera( CameraUpdateFactory.newLatLngZoom(Kuwait,13) );
		 MarkerOptions marker = new MarkerOptions().position(new LatLng(clat, clongt)).title("Your Location ");
		 
		// adding marker
		googleMap.addMarker(marker);
		MarkerOptions marker2 = new MarkerOptions().position(new LatLng(flat, flongt)).title("Ride Location");
		 
		// adding marker
		googleMap.addMarker(marker2);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
	 private void initilizeMap() {
	        if (googleMap == null) {
	            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(
	                    R.id.map)).getMap();
	 
	            // check if map is created successfully or not
	            if (googleMap == null) {
	                Toast.makeText(getApplicationContext(),
	                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
	                        .show();
	            }
	        }
	    }
	 
	    @Override
	    protected void onResume() {
	        super.onResume();
	        initilizeMap();
	    }
	 

}
