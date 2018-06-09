package ictlab.app1.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import ictlab.app1.R;

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        if(!wifi.isWifiEnabled()) {
            checkWifi();
        } else{
            setContentView(R.layout.activity_login);
        }
    }
    public void checkWifi() {
        final WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("The app does not work without Wi-Fi");
        dialog.setMessage("Do you want to turn on Wi-Fi?");
        dialog.setPositiveButton("Turn me on", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                wifi.setWifiEnabled(true);
                System.out.println("clicked yes");
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        wifi.setWifiEnabled(false);
                        System.exit(0);
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    }
    public void loginButtonClick(View view){
        Intent myIntent = new Intent(LoginActivity.this, afterLoginSuccess.class);
        LoginActivity.this.startActivity(myIntent);
    }
}

