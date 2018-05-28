package com.example.crist.writingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


import com.example.crist.writingapp.helpers.APIService;
import com.example.crist.writingapp.models.Address;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BoxdataWritingActivity{
    private Button resetButton;
    private TextView addressTxt;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Add in Oncreate() funtion after setContentView()*/
        resetButton = (Button) findViewById(R.id.button); // initiate Switch

        addressTxt= (TextView) findViewById(R.id.addressTxt);
        // attach an OnClickListener

        this.apiService= new APIService();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher2);

        resetButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                requestBoxdata();

            }
        });
    }

    @Override
    public void onBoxdataResetSuccesful(JSONObject response) {


        Toast toast = Toast.makeText(getApplicationContext(),
                "Reset done!", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void requestBoxdata(){

            //send request with bufferlocation address

            try {
                apiService.putAddressData("2", getOriginAddressParams(),this);
                apiService.putBoxData("10100", getNewBoxParams(),this);

            } catch (JSONException e) {
                e.printStackTrace();
            }

    }



    private RequestParams getNewBoxParams() {
        RequestParams params=new RequestParams();
        params.put("status", "Delivery in progress");
        params.put("customerStatus","Delivered to customer");

        return params;
    }

    private RequestParams getOriginAddressParams() {
        RequestParams params=new RequestParams();

        params.put("name","Ben Baier");
        params.put("str_name","Lombard Street");
        params.put("str_no","4420");
        params.put("city","San Francisco");
        params.put("post_code","94133");
        params.put("state", "California");
        params.put("country","USA");
        return params;
    }


}
