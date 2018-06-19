package ictlab.app1;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ictlab.app1.Booking.pickerDateTime;
import ictlab.app1.Login.afterLoginSuccess;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static java.security.AccessController.getContext;


public class qrCodeClass extends Activity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    public String accessToken, clientToken, uid, lokaalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        Intent intent = getIntent();
        accessToken= intent.getStringExtra("accessToken");
        clientToken = intent.getStringExtra("clientToken");
        uid = intent.getStringExtra("uid");

    }
    public void QRcodeReservation(View view){
        Intent intent = new Intent(qrCodeClass.this, pickerDateTime.class);
        intent.putExtra("accessToken", accessToken);
        intent.putExtra("clientToken", clientToken);
        intent.putExtra("uid", uid);
        intent.putExtra("id", lokaalid);
        qrCodeClass.this.startActivity(intent);

    }



    @Override
    public void handleResult(Result result) {
        setContentView(R.layout.classroomdata);
        final TextView textViewToChange = (TextView) findViewById(R.id.text3);
        textViewToChange.setText(result.getText());
        que(result.getText());
    }

    public void cancelButtonOnClick(View view){
        Intent myIntent = new Intent(qrCodeClass.this, afterLoginSuccess.class);
        qrCodeClass.this.startActivity(myIntent);
    }

    public void que(String id) {
        final TextView textViewToChange = findViewById(R.id.classroom_data);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url =id+".json";
        System.out.println(url);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            textViewToChange.setText("Response is: "+ response);
                            System.out.println(response + "loooool");
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                lokaalid = object.getString("id");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewToChange.setText("No classroom found");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("access-token", accessToken);
                params.put("client", clientToken);
                params.put("uid",uid);
                return params;
            }
        };
        queue.add(arrayRequest);

    }

}
