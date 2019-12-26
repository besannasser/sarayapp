package com.besan98.saray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Register extends AppCompatActivity {
    private FrameLayout frameLayout;
    public static boolean onResetpasswordfragment=false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        frameLayout=findViewById(R.id.register);
        setDefaultFragment(new signinfrag());



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (onResetpasswordfragment){
            onResetpasswordfragment=false;
            setFragment(new signinfrag());
            return  false;

        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

}
