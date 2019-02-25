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
import android.widget.Toast;

import com.example.commuteeazy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasswordFrag extends Fragment {

    TextInputEditText setPassword,repeatPassword;
    Button btn_finish;
    BtnFinishListener listener;
    String accountPassword;

    public static PasswordFrag passwordFrag(){
        PasswordFrag frag = new PasswordFrag();
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
        return inflater.inflate(R.layout.password_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPassword = view.findViewById(R.id.set_password);
        repeatPassword = view.findViewById(R.id.repeat_password);
        btn_finish = view.findViewById(R.id.btn_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(setPassword.getText().toString()) && !TextUtils.isEmpty(repeatPassword.getText().toString())){
                        if ((setPassword.getText().toString()).equals(repeatPassword.getText().toString())){
                            accountPassword = setPassword.getText().toString();
                            listener.onPasswordPass(accountPassword);
                            listener.onBtnFinish();
                        }else {
                            Toast.makeText(getContext(),"Passwords Must Match",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(),"Please fill out all the fields",Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (BtnFinishListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" must implement BtnFinishListener");
        }
    }

    public interface BtnFinishListener{
        public void onBtnFinish();
        public void onPasswordPass(String password);
    }
}
