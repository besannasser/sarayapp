package com.besan98.saray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
ImageView logo;
       Handler handler;
       private FirebaseAuth firebaseAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
             firebaseAuth= FirebaseAuth.getInstance();
            logo= findViewById(R.id.logo);
            Animation myanim = AnimationUtils.loadAnimation(this,R.anim.splashlogo);
            logo.startAnimation(myanim);
            SystemClock.sleep(3000);
                    Intent intent=new Intent(MainActivity.this,Register.class);
                    startActivity(intent);
                    finish();



        }
        @Override
        protected  void onStart(){
            super.onStart();
            FirebaseUser currentuser = firebaseAuth.getCurrentUser();
            if(currentuser==null){
                    Intent registerintent = new Intent(MainActivity.this,Register.class);
                    startActivity(registerintent);
                    finish();
                }else{

                    Intent mainintent = new Intent(MainActivity.this,homeactivity.class);
                    startActivity(mainintent);
                    finish();
                }
            }

        }

