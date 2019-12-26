package com.besan98.saray;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class signupfragm extends Fragment {
private TextView alreadyhaveaccount;
private FrameLayout  parentlayout;

private EditText username;
private EditText phonnumber;
private EditText password;
private EditText passcon;


private Button signup;

private ProgressBar progressBar;
private FirebaseAuth firebaseauthen;
private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

private FirebaseFirestore firebaseFirestore;


    public signupfragm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_signupfragm, container, false);
   alreadyhaveaccount = view.findViewById(R.id.alreadyhaveacc);

   parentlayout = getActivity().findViewById(R.id.register);

   phonnumber =view.findViewById(R.id.email);
   username= view.findViewById(R.id.username);
   password = view.findViewById(R.id.password);
   passcon = view.findViewById(R.id.conpass);

   signup = view.findViewById(R.id.signup);
   progressBar = view.findViewById(R.id.progresssignup);
   firebaseauthen =  FirebaseAuth.getInstance();
   firebaseFirestore= FirebaseFirestore.getInstance();




   return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new signinfrag() );

            }
        });
        phonnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
       password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passcon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send data to server
                checkemailandpass();
            }
        });

    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(parentlayout.getId(),fragment);
        fragmentTransaction.commit();
}
    @SuppressLint("ResourceAsColor")
    private void checkinput(){
        if(!TextUtils.isEmpty(username.getText())){
            if (!TextUtils.isEmpty(phonnumber.getText())){
                    if (!TextUtils.isEmpty(password.getText())&& password.length() >= 8) {
                        if (!TextUtils.isEmpty(passcon.getText())){
                            signup.setEnabled(true);
                            signup.setTextColor(R.color.colorAccent);
                        }else {
                            signup.setEnabled(false);
                            signup.setTextColor(R.color.bttondis);
                        }
                    }else{
                        signup.setEnabled(false);
                        signup.setTextColor(R.color.bttondis);
                    }
                }else{
                signup.setEnabled(false);
                signup.setTextColor(R.color.bttondis);
                }
            }else{
signup.setEnabled(false);
signup.setTextColor(R.color.bttondis);
            }


    }
    @SuppressLint("ResourceAsColor")
    private void checkemailandpass(){

        if(phonnumber.getText().toString().matches(emailPattern)) {
            if(password.getText().toString().equals((passcon.getText().toString()))){
                progressBar.setVisibility(View.VISIBLE);
                firebaseauthen.createUserWithEmailAndPassword(phonnumber.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Map<Object,String> userdata = new HashMap<>();
                            userdata.put("username",username.getText().toString());
                            firebaseFirestore.collection("USERS").add(userdata).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful()){
                                        mainIntent();

                                    }else{
                                        progressBar.setVisibility(View.INVISIBLE);
                                        signup.setEnabled(true);
                                        signup.setTextColor(R.color.colorAccent);
                                        String error = task.getException().getMessage();
                                        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else{
                            progressBar.setVisibility(View.INVISIBLE);
                            signup.setEnabled(true);
                            signup.setTextColor(R.color.colorAccent);
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
               passcon.setError("Password doesnt match ");
            }

    }else{
            phonnumber.setError("Invalid Email");

        }

}
private void mainIntent(){
    Intent mainintent = new Intent(getActivity(),homeactivity.class);
    startActivity(mainintent);
    getActivity().finish();
}
}
