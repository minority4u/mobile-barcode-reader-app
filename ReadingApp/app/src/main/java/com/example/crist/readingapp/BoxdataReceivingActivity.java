package com.example.crist.readingapp;

import org.json.JSONObject;

public interface BoxdataReceivingActivity {
    public void onBoxdataReceived(JSONObject response);

    void onAddressDataChanged(JSONObject response);

    void onBoxDataChanged(JSONObject response);
}
