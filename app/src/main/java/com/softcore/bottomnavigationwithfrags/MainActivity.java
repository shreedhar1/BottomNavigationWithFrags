package com.softcore.bottomnavigationwithfrags;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.window.OnBackInvokedDispatcher;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    BottomNavigationView bottomNavigationView;
    private long backPressedTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        showhome();


        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            fragment = null;
            if (item.getItemId() == R.id.nav_home) {
                fragment = new Home_Fragment();
                Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
            }else if(item.getItemId() == R.id.nav_setting){
                fragment = new Setting_Fragment();
                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();

            }
            else if(item.getItemId() == R.id.nav_profile){
                fragment = new Profile_Fragment();
                Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();

            }
            else if(item.getItemId() == R.id.nav_exit){
                Logout();
                Toast.makeText(MainActivity.this, "logout", Toast.LENGTH_SHORT).show();
            }


            if(fragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.f_container,
                        fragment).commit();
            }



//            FragmentManager manager = getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.f_container,selectedFragment,selectedFragment.getTag()).commit();

            return true;
        }
    };

    private void Logout() {

    }

    private void showhome() {

        fragment = new Home_Fragment();

        if (fragment != null){
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.f_container,fragment,fragment.getTag()).commit();
        }
        bottomNavigationView.setSelectedItemId(R.id.nav_home); //select home on bottom navigation also
    }

    @Override
    public void onBackPressed() {
       if(fragment instanceof Home_Fragment)
        { if (backPressedTime + 2000 > System.currentTimeMillis()) {
                backToast.cancel();
                super.onBackPressed();

            } else {
                backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
       else {
           showhome();
       }
    }



}