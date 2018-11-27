package com.example.michelle.driverbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DriverMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView name,email,license;

    public Button payfine_bttn;

    public void setPayfine_bttn()
    {
        payfine_bttn=(Button)findViewById(R.id.payfine_bttn);
        payfine_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity= new Intent(DriverMenu.this,Payment.class);
                startActivity(nextActivity);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_menu);
        setPayfine_bttn();


        name=findViewById(R.id.driverProfileName);
        email=findViewById(R.id.driverProfileEmail);
        license=findViewById(R.id.driverProfileLicense);

        SharedPreferences preferences = getSharedPreferences("driverDetails",MODE_PRIVATE);
        name.setText(preferences.getString("Name","N/A"));
        email.setText(preferences.getString("Email","N/A"));
        license.setText(preferences.getString("License","N/A"));

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        NavigationView navigationView =findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_histroy:
                Intent newActivity= new Intent(DriverMenu.this,FineHistory.class);
                startActivity(newActivity);
                break;

            case R.id.nav_setting:
                Intent driverEditActivity= new Intent(DriverMenu.this,DriverProfileEditActivity.class);
                startActivity(driverEditActivity);
                break;

            case R.id.nav_logout:
                Intent logOutActivity=new Intent(DriverMenu.this,Logging.class);
                startActivity(logOutActivity);
                finish();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
