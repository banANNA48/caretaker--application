package com.thesis2020.monitoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PickParticipant extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_participant);

        Spinner spinnerOptionVIP = findViewById(R.id.spinnerOptionVIP);


        final String[] VIPList = getResources().getStringArray(R.array.VIP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.VIP, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptionVIP.setAdapter(adapter);
        spinnerOptionVIP.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String VIPNames = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(),VIPNames,Toast.LENGTH_SHORT).show();

            if (parent.getItemAtPosition(position).equals("Choose VIP")) {
                //do nothing
            }
            else if (parent.getItemAtPosition(position).equals("Catherine Cruz")) {
                Intent intent=new Intent(PickParticipant.this,NavProfileVIP.class);
                //intent.putExtra("ChosenVIPName",CatherineCruz);
                startActivity(intent);
            }
            else if (parent.getItemAtPosition(position).equals("Rona Camarines")) {
                Intent intent=new Intent(PickParticipant.this,VIPProfileRona.class);
                //intent.putExtra("ChosenVIPName",RonaCamarines);
                startActivity(intent);
            }

    }

        @Override
        public void onNothingSelected (AdapterView < ? > parent){

        }
    }
