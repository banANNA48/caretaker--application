package com.thesis2020.monitoringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class VIPProfileRona extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Button locateVIP;
    private static final int PERMISSION_REQUEST = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_i_p_profile_rona);

        setUpToolbar();
        navigationView = findViewById(R.id.navigation_menu);
        locateVIP = findViewById(R.id.btnlocate);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_myProfile:

                        Intent intentmyProfile = new Intent(VIPProfileRona.this, MainActivity.class);
                        startActivity(intentmyProfile);
                        break;

                    case R.id.nav_vipProfile:

                        Intent intentvipProfile = new Intent(VIPProfileRona.this, NavProfileVIP.class);
                        startActivity(intentvipProfile);
                        break;

                    case R.id.fall_reports:
                        Intent logFall = new Intent(VIPProfileRona.this, FallReports.class);
                        startActivity(logFall);
                        break;

                    case R.id.changeVip:

                        Intent intentchangeVIP = new Intent(VIPProfileRona.this, PickParticipant.class);
                        startActivity(intentchangeVIP);
                        break;

                    case R.id.nav_logout:

                        FirebaseAuth.getInstance().signOut();//logout
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                        break;

                    case  R.id.nav_share:

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                        break;
                }//switch

                return false;
            }//MENU
        }); //sidebar


    } //oncreate


    public void setUpToolbar() {

        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }//toolbar

    public void SendSMS(View view) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED){
            MyMessage();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST);
        }
    }//send SMS

    private void MyMessage() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+639438624204", null, "LOCATE", null, null);
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    } //Message

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                MyMessage();
            } else {
                Toast.makeText(this, "You don't have required permission to send a message", Toast.LENGTH_SHORT).show();
            }
        }
    }//Request Permission
}