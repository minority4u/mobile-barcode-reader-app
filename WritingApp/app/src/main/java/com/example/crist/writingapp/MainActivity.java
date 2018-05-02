package com.example.crist.writingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


import com.example.crist.writingapp.helpers.APIService;
import com.example.crist.writingapp.models.Address;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.RequestParams;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BoxdataWritingActivity{
    private Switch simpleSwitch;
    private Boolean switchOn=false;
    private TextView addressTxt;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Add in Oncreate() funtion after setContentView()*/
        simpleSwitch = (Switch) findViewById(R.id.switch1); // initiate Switch

        addressTxt= (TextView) findViewById(R.id.addressTxt);
        // attach an OnClickListener

        this.apiService= new APIService();

        simpleSwitch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                requestBoxdata();
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Toggle to +"+!switchOn+"!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public void onBoxdataReceive(JSONObject response) {
        final ObjectMapper mapper = new ObjectMapper();

        try {
            Address address = mapper.readValue(response.toString(), Address.class);
            addressTxt.setText(address.toString());

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                "Response received!", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void requestBoxdata(){
        if(switchOn){
            //send request with bufferlocation address

            try {
                apiService.putAddressData("2", getBufferlocationParams(),this);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            switchOn=!switchOn;
        }else{
            //send request with buyer address
            try {
                apiService.putAddressData("2", getCustomerParams(),this);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            switchOn=!switchOn;
        }
    }

    private RequestParams getBufferlocationParams(){
        RequestParams params=new RequestParams();
        params.put("name","Packstation No. 606");
        params.put("str_name","Lange Roetterstraße");
        params.put("str_no","20");
        params.put("city","Mannheim");
        params.put("post_code","68167");
        params.put("country","Germany");
        return params;
    }

    private RequestParams getCustomerParams(){
        RequestParams params=new RequestParams();
        params.put("name","Paul Happy");
        params.put("str_name","Exampleroad");
        params.put("str_no","42");
        params.put("city","Schlumpfhausen");
        params.put("post_code","12345");
        params.put("country","Schlumpfland");
        return params;
    }
}
