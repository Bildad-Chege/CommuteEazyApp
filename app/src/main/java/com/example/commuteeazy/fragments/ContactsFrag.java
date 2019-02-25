package com.example.commuteeazy.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.commuteeazy.R;

import java.math.BigInteger;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFrag extends Fragment {

    TextInputEditText mobile,email;
    Button next;
    BtnContactsListener listener;
    Long phoneNumber;
    String emailAddress;


    public static ContactsFrag contactsFrag(){
        ContactsFrag frag = new ContactsFrag();
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contacts_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mobile = view.findViewById(R.id.phone_number);
        email = view.findViewById(R.id.email_address);
        next = view.findViewById(R.id.next_from_contacts);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobile.getText()!=null&&email.getText()!=null){
                    phoneNumber = Long.valueOf(mobile.getText().toString());
                    emailAddress = email.getText().toString();
                    listener.onContactsPass(phoneNumber,emailAddress);
                    listener.onBtnContacts();
                }else {
                    Toast.makeText(getContext(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (BtnContactsListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement BtnContactsListener");
        }
    }

    public interface BtnContactsListener{
        public void onBtnContacts();
        public void onContactsPass(Long phone,String email);
    }
}
