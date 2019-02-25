package com.example.commuteeazy.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.commuteeazy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationFrag extends Fragment {
    EditText vcode;
    Button next;
    BtnVerificationListener listener;

    public static VerificationFrag verificationFrag(){
        VerificationFrag frag = new VerificationFrag();
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstance){
        super.onCreate(savedInstance);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.verification_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstance){
        vcode = view.findViewById(R.id.verification_code);
        next = view.findViewById(R.id.next_from_verification);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBtnVerification();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (BtnVerificationListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement BtnVerificationListener");
        }
    }

    public interface BtnVerificationListener{
        public void onBtnVerification();
    }
}


