package com.besan98.saray;

import android.app.Activity;
import android.os.Bundle;

import com.besan98.saray.ui.home.HomeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.FrameLayout;

public class homeactivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

       navigationView.getMenu().getItem(0).setChecked(true);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        frameLayout = findViewById(R.id.main_frame);
        setFragment(new HomeFragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id =item.getItemId();
        if (id==R.id.search){

            return true;
        }else if (id == R.id.notification){
            return true;
        }else if(id ==R.id.cart){
            return true ;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
     int id = item.getItemId();
       if (id==R.id.nav_signout){

      }
     else if (id==R.id.nav_favourite){

     }else  if (id==R.id.nav_mycart){

     }else  if (id==R.id.nav_famous){

     }else  if (id==R.id.nav_account){

     }
     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
     drawer.closeDrawer(GravityCompat.START);
     return  true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homeactivity, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }
 private  void setFragment(Fragment fragment){
     FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
     fragmentTransaction.replace(frameLayout.getId(),fragment);
     fragmentTransaction.commit();
 }
}
