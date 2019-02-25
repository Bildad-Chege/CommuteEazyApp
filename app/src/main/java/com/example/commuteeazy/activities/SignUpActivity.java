package com.example.commuteeazy.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.commuteeazy.Config;
import com.example.commuteeazy.DO.User;
import com.example.commuteeazy.R;
import com.example.commuteeazy.fragments.ContactsFrag;
import com.example.commuteeazy.fragments.NamesFrag;
import com.example.commuteeazy.fragments.PasswordFrag;
import com.example.commuteeazy.fragments.VerificationFrag;
import com.example.commuteeazy.network.JSONParser;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity implements NamesFrag.BtnNamesListener, VerificationFrag.BtnVerificationListener, ContactsFrag.BtnContactsListener, PasswordFrag.BtnFinishListener {

    Fragment nfragment,cfragment,vfragment,pfragment;
    FragmentTransaction transaction1,transaction2,transaction3,transaction4;
    String firstName,lastName,userName,emailAddress,password;
    Long mobile;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        transaction1 = getSupportFragmentManager().beginTransaction();
        nfragment = NamesFrag.namesFrag();
        transaction1.add(R.id.frame,nfragment);
        transaction1.commit();
    }

    @Override
    public void onBtnNames() {
        cfragment = ContactsFrag.contactsFrag();
        transaction2 = getSupportFragmentManager().beginTransaction();
        transaction2.replace(R.id.frame,cfragment);
        transaction2.addToBackStack(null);
        transaction2.commit();
    }

    @Override
    public void onNamesPass(String fName, String lName, String uName) {
        firstName = fName;
        lastName = lName;
        userName = uName;
        user.setFirstName(firstName);
        user.setSecondName(lastName);
        user.setUserName(userName);
    }

    @Override
    public void onBtnContacts() {
        vfragment = VerificationFrag.verificationFrag();
        transaction3 = getSupportFragmentManager().beginTransaction();
        transaction3.replace(R.id.frame,vfragment);
        transaction3.addToBackStack(null);
        transaction3.commit();
    }

    @Override
    public void onContactsPass(Long phone, String email) {
        mobile = phone;
        emailAddress = email;
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
        user.setEmailAddress(emailAddress);
        user.setPhoneNumber(mobile);
    }

    @Override
    public void onBtnVerification() {
        pfragment = PasswordFrag.passwordFrag();
        transaction4 = getSupportFragmentManager().beginTransaction();
        transaction4.replace(R.id.frame,pfragment);
        transaction4.addToBackStack(null);
        transaction4.commit();
    }

    @Override
    public void onBtnFinish() {
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()){
            new saveUserData().execute();
        } else {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPasswordPass(String password) {
        this.password = password;
        user.setAccountPassword(this.password);
    }

    public class saveUserData extends AsyncTask<String,String,String>{

        ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
        int status;
        String message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait ....");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            JSONParser parser = new JSONParser();
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("firstName",user.getFirstName()));
            params.add(new BasicNameValuePair("lastName",user.getSecondName()));
            params.add(new BasicNameValuePair("userName",user.getUserName()));
            params.add(new BasicNameValuePair("phone",user.getPhoneNumber().toString()));
            params.add(new BasicNameValuePair("email",user.getEmailAddress()));
            params.add(new BasicNameValuePair("password",user.getAccountPassword()));

            JSONObject object = parser.makeHttpRequest(Config.hostName+Config.signup,params);
            try {
                status = object.getInt("status");
                message = object.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }
    }
}
