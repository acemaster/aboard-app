package com.vivekapp.android.aboard;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class RegisterActivity extends Activity {
    EditText email,password,fullname,telno;
    Button login,register,getloc;
    double currentlat,currentlong;
    String emailtxt,passwordtxt,fullnametxt,telnotxt,lat,longt;
    List<NameValuePair> params;
    String noofrides;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        fullname=(EditText)findViewById(R.id.fullname);
        telno=(EditText)findViewById(R.id.telno);
        register = (Button)findViewById(R.id.registerbtn);
        login = (Button)findViewById(R.id.login);
        noofrides="0";
       
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(regactivity);
                finish();
            }
        });
      
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                fullnametxt=fullname.getText().toString();
                telnotxt=telno.getText().toString();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", emailtxt));
                params.add(new BasicNameValuePair("password", passwordtxt));
                params.add(new BasicNameValuePair("fullname", fullnametxt));
                params.add(new BasicNameValuePair("telno", telnotxt));
                params.add(new BasicNameValuePair("noofrides",noofrides));
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://powerful-tor-5829.herokuapp.com/register",params);
                //JSONObject json = sr.getJSON("http://192.168.56.1:8080/register",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
                        Log.d("Hello", jsonstr);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}