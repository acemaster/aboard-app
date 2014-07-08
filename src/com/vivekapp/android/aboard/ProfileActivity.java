package com.vivekapp.android.aboard;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.internal.widget.IcsToast;

import java.util.ArrayList;
import java.util.List;
public class ProfileActivity extends SherlockFragment {
    
	
	
	
	SharedPreferences pref;
    String token,grav,oldpasstxt,newpasstxt;
    String name,email;
    TextView nametxt,emailtxt;
    
    Button chgpass,chgpassfr,cancel,logout,currentloc;
    Dialog dlg;
    EditText oldpass,newpass;
    List<NameValuePair> params;
    
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile, container, false);
        return view;
    }
    @Override
    public void onActivityCreated (Bundle savedInstanceState)
    {
    super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);
 
        chgpass = (Button)getActivity().findViewById(R.id.chgbtn);
        logout = (Button)getActivity().findViewById(R.id.logout);
       
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = pref.edit();
                //Storing Data using SharedPreferences
                edit.putString("token", "");
                edit.commit();
                Intent loginactivity = new Intent(getActivity(),LoginActivity.class);
                startActivity(loginactivity);
               
            }
        });
        nametxt=(TextView)getActivity().findViewById(R.id.nameprofile);
        emailtxt=(TextView)getActivity().findViewById(R.id.emailprofile);
        pref = getActivity().getBaseContext().getSharedPreferences("AppPref",Context.MODE_PRIVATE);
        token = pref.getString("token", "");
        name=pref.getString("name", "");
        email=pref.getString("email", "");
        nametxt.setText(name);
        emailtxt.setText(email);
       
        chgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg = new Dialog(getActivity());
                dlg.setContentView(R.layout.chgpassword_frag);
                dlg.setTitle("Change Password");
                chgpassfr = (Button)dlg.findViewById(R.id.chgbtn);
                chgpassfr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        oldpass = (EditText)dlg.findViewById(R.id.oldpass);
                        newpass = (EditText)dlg.findViewById(R.id.newpass);
                        oldpasstxt = oldpass.getText().toString();
                        newpasstxt = newpass.getText().toString();
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("oldpass", oldpasstxt));
                        params.add(new BasicNameValuePair("newpass", newpasstxt));
                        params.add(new BasicNameValuePair("id", token));
                        ServerRequest sr = new ServerRequest();
                    //    JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/chgpass",params);
                        JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com/api/chgpass",params);
                        if(json != null){
                            try{
                                String jsonstr = json.getString("response");
                                if(json.getBoolean("res")){
                                dlg.dismiss();
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
                cancel = (Button)dlg.findViewById(R.id.cancelbtn);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dlg.dismiss();
                    }
                });
                dlg.show();
            }
        });
        
            }
  
   
    }
