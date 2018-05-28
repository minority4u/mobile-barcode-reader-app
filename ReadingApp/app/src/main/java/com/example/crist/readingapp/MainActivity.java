package com.example.crist.readingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import helpers.APIService;
import model.Box;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BoxdataReceivingActivity{
    private Button scanBarcodeBtn, scanNFCBtn, returnButton;
    private TextView contentTxt, statusTxt;
    private APIService apiService;
    private Box currentBox;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBarcodeBtn = (Button)findViewById(R.id.scan_barcode_button);
        scanNFCBtn =(Button)findViewById(R.id.scan_nfc_button);
         returnButton =(Button)findViewById(R.id.return_button);
        contentTxt = (TextView)findViewById(R.id.box_address_textView);
        statusTxt = (TextView)findViewById(R.id.box_status_textView);

        scanBarcodeBtn.setOnClickListener(this);
        scanNFCBtn.setOnClickListener(this);
        returnButton.setOnClickListener(this);

        this.apiService= new APIService();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher2);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.scan_barcode_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
        else if (view.getId()==R.id.scan_nfc_button){
            Intent myIntent = new Intent(MainActivity.this, NFCActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
        else if (view.getId()==R.id.return_button){
            startReturn();
        }
    }

    private void startReturn() {
        try {
            apiService.putAddressData("2", getNewAddressParams(),this);
            apiService.putBoxData(currentBox.id, getNewBoxParams(),this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private RequestParams getNewBoxParams() {
        RequestParams params=new RequestParams();

        params.put("customerStatus","Return triggered");

        return params;
    }

    private RequestParams getNewAddressParams() {
        RequestParams params=new RequestParams();

             params.put("name","Regina Rebai");
             params.put("str_name","Haven Avenue");
             params.put("str_no","42");
             params.put("city","Menlo Park");
             params.put("post_code","94025");
             params.put("state", "California");
             params.put("country","USA");


        return params;
    }

    //Show content of barcode scanning
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();


//            idTxt.setText("ID: " + scanFormat);
//            contentTxt.setText("Address: " + scanContent);
            try {
                apiService.getBoxData(scanContent, this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public void onBoxdataReceived(JSONObject response){
         System.out.println("onBoxdataReceived: "+response.toString());
         String address="";
         final ObjectMapper mapper = new ObjectMapper();

         try {
            currentBox = mapper.readValue(response.toString(), Box.class);
            statusTxt.setText(currentBox.getBoxStatusToString());
            contentTxt.setText(currentBox.getBoxContentToString());


        } catch (JsonParseException e) {
             e.printStackTrace();
         } catch (JsonMappingException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @Override
    public void onAddressDataChanged(JSONObject response) {

    }

    @Override
    public void onBoxDataChanged(JSONObject response) {
        String address="";
        final ObjectMapper mapper = new ObjectMapper();

        try {
            currentBox = mapper.readValue(response.toString(), Box.class);
            statusTxt.setText(currentBox.getBoxStatusToString());
            contentTxt.setText(currentBox.getBoxContentToString());


        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(getApplicationContext(),
                "Your return is triggered!", Toast.LENGTH_SHORT);
        toast.show();

        statusTxt.setText(currentBox.getBoxStatusToString());
    }

}

