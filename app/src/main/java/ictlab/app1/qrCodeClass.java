package ictlab.app1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;

import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;


import org.json.JSONObject;

import ictlab.app1.Main.afterLoginSuccess;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by edgar on 4-3-2018.
 */

public class qrCodeClass extends Activity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }


    @Override
    public void handleResult(Result result) {
        //Do anything with result here :D
        setContentView(R.layout.classroomdata);
        final TextView textViewToChange = (TextView) findViewById(R.id.text3);
        textViewToChange.setText(result.getText());
        que(result.getText());
    }

    public void cancelButtonOnClick(View view){
        Intent myIntent = new Intent(qrCodeClass.this, afterLoginSuccess.class);
        qrCodeClass.this.startActivity(myIntent);
    }

    public void MakeReservationButtonOnClick(View view){
        setContentView(R.layout.qr_code);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    public void que(String id) {
        final TextView textViewToChange = (TextView) findViewById(R.id.classroom_data);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://145.24.222.187:3000/classrooms/"+ id +".json";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject obj = new JSONObject();
                        // Display the first 500 characters of the response string.
                        textViewToChange.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewToChange.setText("No classroom found");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
