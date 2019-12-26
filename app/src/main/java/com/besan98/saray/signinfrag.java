package com.besan98.saray;


import android.annotation.SuppressLint;
import android.content.Intent;
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

import static com.besan98.saray.Register.onResetpasswordfragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class signinfrag extends Fragment {


    public signinfrag() {
        // Required empty public constructor
    }
    private TextView donthaveaccount,forgetpass;
    private FrameLayout parentlayout;
    private EditText email;
    private EditText password;
    private Button signinbtn;

    private FirebaseAuth firebaseAuth;
    private String emailpattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private ProgressBar progressBar;

    private TextView skip;

    private TextView forgotpassword;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_signinfrag, container, false);
   donthaveaccount = view.findViewById(R.id.newaccount);
   parentlayout=getActivity().findViewById(R.id.register);
   email= view.findViewById(R.id.editphonnumber);
   password= view.findViewById(R.id.editTpassword);

   signinbtn = view.findViewById(R.id.login);

   firebaseAuth = FirebaseAuth.getInstance();

   progressBar= view.findViewById(R.id.progresssignin);
   skip = view.findViewById(R.id.Skip);
   forgotpassword = view.findViewById(R.id.forgetpass);


   return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetpasswordfragment=true;
                setFragment(new forgetpassword());
            }
        });

        donthaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new signupfragm());

            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIntent();

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              checkInputs();
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
              checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkemailandpass();
            }
        });

    } private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentlayout.getId(),fragment);
        fragmentTransaction.commit();}
    @SuppressLint("ResourceAsColor")
    private void checkInputs(){
        if (!TextUtils.isEmpty((email.getText()))){
            if (!TextUtils.isEmpty((email.getText()))){

            }else{

            }
        }else{

        }
    }
    @SuppressLint("ResourceAsColor")
    private void checkemailandpass(){
        if (email.getText().toString().matches(emailpattern)){
            if (password.length()>=8){
                progressBar.setVisibility(View.VISIBLE);


firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){
                mainIntent();
        }else{
            progressBar.setVisibility(View.INVISIBLE);

            String error = task.getException().getMessage();
            Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
        }

    }
});
            }else{
                Toast.makeText(getActivity(),"Incorrect email or password ",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),"Incorrect email or password ",Toast.LENGTH_SHORT).show();
        }

    }
    private void mainIntent(){
        Intent mainintent = new Intent(getActivity(),homeactivity.class);
        startActivity(mainintent);
        getActivity().finish();
    }

}
