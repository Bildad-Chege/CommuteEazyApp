package com.example.commuteeazy.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.commuteeazy.R;
import com.example.commuteeazy.activities.SignUpActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NamesFrag extends Fragment {

    TextInputEditText firstName,lastName,userName;
    Button next;
    BtnNamesListener listener;
    String fName,lName,uName;

    public static NamesFrag namesFrag(){
        NamesFrag frag = new NamesFrag();
        return frag;
    }

    public void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.names_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstance){
        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.second_name);
        userName = view.findViewById(R.id.user_name);
        next = view.findViewById(R.id.btn_next_from_names);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(firstName.getText().toString()) &&!TextUtils.isEmpty(lastName.getText().toString())&&!TextUtils.isEmpty(userName.getText().toString())){
                    fName = firstName.getText().toString();
                    lName = lastName.getText().toString();
                    uName = userName.getText().toString();
                    listener.onNamesPass(fName,lName,uName);
                    listener.onBtnNames();
                }else {
                    Toast.makeText(getContext(),"Please Fill out all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (BtnNamesListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement BtnNamesListener");
        }
    }

    public interface BtnNamesListener{
        public void onBtnNames();
        public void onNamesPass(String fName,String lName,String uName);
    }

}




