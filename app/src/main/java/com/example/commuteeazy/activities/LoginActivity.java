package com.example.commuteeazy.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.commuteeazy.Config;
import com.example.commuteeazy.MapsActivity;
import com.example.commuteeazy.R;
import com.example.commuteeazy.network.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity  {
    EditText username,password;
    Button login;
    TextView link;
    String s_userName,s_password;
    final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    boolean emailValidate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.user_name);
        password = findViewById(R.id.loginpassword);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(username.getText().toString())&&!TextUtils.isEmpty(password.getText().toString())){
                    ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info = cm.getActiveNetworkInfo();
                    if(info != null && info.isConnectedOrConnecting()){
                        Config.loginText = username.getText().toString();
                        Config.password = password.getText().toString();
                        try{
                            new LoginUser().execute();
                        } catch (Exception e){
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Please enter all Credentials",Toast.LENGTH_SHORT).show();
                }
            }
        });
        link = findViewById(R.id.signup_link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public class LoginUser extends AsyncTask<String,String,String>{

        ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        int status;
        String message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait ...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            List<NameValuePair> params = new ArrayList<>();
            //params.add(new BasicNameValuePair());
            if (emailValidate(Config.loginText)){
                params.add(new BasicNameValuePair("email",Config.loginText));
                params.add(new BasicNameValuePair("password",Config.password));


                JSONObject object = parser.makeHttpRequest(Config.hostName+Config.login,params);
                try {
                    status = object.getInt("status");
                    message = object.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                params.add(new BasicNameValuePair("userName",Config.loginText));
                params.add(new BasicNameValuePair("password",Config.password));

                JSONObject object = parser.makeHttpRequest(Config.hostName+Config.login,params);

                try {
                    status = object.getInt("status");
                    message = object.getString("message");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if (status == 1){
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
            }
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }
    }
}
