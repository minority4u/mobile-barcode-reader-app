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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import helpers.APIService;
import model.Box;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, BoxdataReceivingActivity{
    private Button scanBarcodeBtn, scanNFCBtn;
    private TextView addressTxt, statusTxt;
    private APIService apiService;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBarcodeBtn = (Button)findViewById(R.id.scan_barcode_button);
        scanNFCBtn =(Button)findViewById(R.id.scan_nfc_button);
        addressTxt = (TextView)findViewById(R.id.box_address_textView);
        statusTxt = (TextView)findViewById(R.id.box_status_textView);

        scanBarcodeBtn.setOnClickListener(this);
        scanNFCBtn.setOnClickListener(this);

        this.apiService= new APIService();
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
    }

    //Show content of barcode scanning
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();


//            idTxt.setText("ID: " + scanFormat);
//            addressTxt.setText("Address: " + scanContent);
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
            Box box = mapper.readValue(response.toString(), Box.class);
            statusTxt.setText(box.getBoxStatusToString());
            addressTxt.setText(box.getBoxDestinationAddressToString());


        } catch (JsonParseException e) {
             e.printStackTrace();
         } catch (JsonMappingException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

}

