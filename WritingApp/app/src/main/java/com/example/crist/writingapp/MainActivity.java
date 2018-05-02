package com.example.crist.writingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements BoxdataWritingActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Add in Oncreate() funtion after setContentView()*/
        Switch simpleSwitch = (Switch) findViewById(R.id.switch1); // initiate Switch


    }

    @Override
    public void onBoxdataSend(JSONObject response) {

    }
}
