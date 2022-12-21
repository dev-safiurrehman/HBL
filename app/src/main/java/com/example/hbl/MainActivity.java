package com.example.hbl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
            Toolbar toolbar;
String url = "com.hbl.bbcustomerapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
                toolbar=findViewById(R.id.toolbar);
                        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.OpenDrawer);
                drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        loadFragment(new home());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             int id = item.getItemId();
             if (id==R.id.Custsprt){

                 loadFragment(new csFragment());

             }else if(id==R.id.knt){
                 try {
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + url)));
                 } catch (android.content.ActivityNotFoundException anfe) {
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + url)));
                 }

             }else if(id==R.id.Deal){
                 loadFragment(new dFragment());

             }else if(id==R.id.rewards){
                 loadFragment(new rFragment());

             }else if(id==R.id.plan){
                 loadFragment(new PlanFragment());

             }else if(id==R.id.loct){
                 loadFragment(new lFragment());

             }else if(id==R.id.Schdl){
                 loadFragment(new sFragment());

             }else {
                 loadFragment(new nFragment());

             }





                return true;
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.commit();
    }
}